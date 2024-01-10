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
import java.util.Optional;

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
        return SignInResponse.of(getToken(getMember(socialAccessToken, request)));
    }

    private Member getMember(String socialAccessToken, SignInRequest request) {
        SocialType socialType = request.socialType();
        String socialId = getSocialId(socialAccessToken, socialType);
        return signUp(socialType, socialId);
    }

    private String getSocialId(String socialAccessToken, SocialType socialType) {
        return switch (socialType) {
            case KAKAO -> kakaoService.getKakaoData(socialAccessToken);
            default -> throw new IllegalArgumentException(INVALID_TOKEN.getMessage());
        };
    }

    private Member signUp(SocialType socialType, String socialId) {
        return memberRepository.findBySocialTypeAndSocialId(socialType, socialId)
                .orElseGet(() -> saveMember(socialType, socialId));
    }

    private Member saveMember(SocialType socialType, String socialId) {
        Member member = Member.builder()
                .socialType(socialType)
                .socialId(socialId)
                .build();
        memberRepository.save(member);
        return member;
    }

    private Token getToken(Member member) {
        Token token = generateToken(new UserAuthentication(member.getId(), null, null));
        member.updateRefreshToken(token.getRefreshToken());
        return token;
    }

    private Token generateToken(Authentication authentication) {
        return new Token(jwtTokenProvider.generateToken(authentication, valueConfig.getAccessTokenExpired()),
                jwtTokenProvider.generateToken(authentication, valueConfig.getRefreshTokenExpired()));
    }
}
