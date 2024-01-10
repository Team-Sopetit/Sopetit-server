package com.soptie.server.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.soptie.server.auth.dto.SignInRequest;
import com.soptie.server.auth.dto.SignInResponse;
import com.soptie.server.auth.jwt.JwtTokenProvider;
import com.soptie.server.auth.jwt.UserAuthentication;
import com.soptie.server.auth.vo.Token;
import com.soptie.server.common.config.ValueConfig;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.entity.SocialType;
import com.soptie.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static com.soptie.server.auth.message.ErrorMessage.INVALID_MEMBER;
import static com.soptie.server.auth.message.ErrorMessage.INVALID_TOKEN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final KakaoService kakaoService;
    private final ValueConfig valueConfig;

    @Transactional
    public SignInResponse signIn(String socialAccessToken, SignInRequest request) {
        Member member = processMemberTasks(socialAccessToken, request);
        return SignInResponse.of(processTokenTasks(member));
    }

    private Member processMemberTasks(String socialAccessToken, SignInRequest request) {
        SocialType socialType = request.socialType();
        String socialId = login(socialAccessToken, socialType);
        signUp(socialType, socialId);
        return getMember(socialType, socialId);
    }

    private String login(String socialAccessToken, SocialType socialType) {
        return switch (socialType.toString()) {
            case "KAKAO" -> kakaoService.getKakaoData(socialAccessToken);
            default -> throw new IllegalArgumentException(INVALID_TOKEN.getMessage());
        };
    }

    private void signUp(SocialType socialType, String socialId) {
        if (!checkMemberExist(socialType, socialId)) {
            memberRepository.save(
                    Member.builder()
                    .socialType(socialType)
                    .socialId(socialId)
                    .build());
        }
    }

    private boolean checkMemberExist(SocialType socialType, String socialId) {
        return memberRepository.existsBySocialTypeAndSocialId(socialType, socialId);
    }

    private Member getMember(SocialType socialType, String socialId) {
        return memberRepository.findBySocialTypeAndSocialId(socialType, socialId)
                .orElseThrow(() -> new RuntimeException(INVALID_MEMBER.getMessage()));
    }

    private Token processTokenTasks(Member member) {
        Token token = generateToken(new UserAuthentication(member.getId(), null, null));
        member.updateRefreshToken(token.getRefreshToken());
        return token;
    }

    private Token generateToken(Authentication authentication) {
        return new Token(jwtTokenProvider.generateToken(authentication, valueConfig.getAccessTokenExpired()),
                jwtTokenProvider.generateToken(authentication, valueConfig.getRefreshTokenExpired()));
    }

    public String getKakaoAccessToken(String code) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Accept", "application/json");

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", valueConfig.getClientId());
        body.add("redirect_uri", valueConfig.getRedirectUri());
        body.add("client_secret", valueConfig.getClientSecret());
        body.add("code", code);

        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(body, headers);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                valueConfig.getTokenUri(),
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        String responseBody = response.getBody();

        if(Objects.isNull(responseBody)) throw new IllegalArgumentException("유효하지 않은 토큰");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        return jsonNode.get("access_token").asText();
    }
}
