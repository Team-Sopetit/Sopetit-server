package com.soptie.server.auth.service;

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
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.soptie.server.auth.message.ErrorMessage.INVALID_TOKEN;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final KakaoService kakaoService;
    private final ValueConfig valueConfig;

    @Override
    @Transactional
    public SignInResponse signIn(String socialAccessToken, SignInRequest request) {
        return SignInResponse.of(getToken(getMember(socialAccessToken, request)));
    }

    private Member getMember(String socialAccessToken, SignInRequest request) {
        val socialType = request.socialType();
        val socialId = getSocialId(socialAccessToken, socialType);
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
        val member = Member.builder()
                .socialType(socialType)
                .socialId(socialId)
                .build();
        return memberRepository.save(member);
    }

    private Token getToken(Member member) {
        val token = generateToken(new UserAuthentication(member.getId(), null, null));
        member.updateRefreshToken(token.getRefreshToken());
        return token;
    }

    private Token generateToken(Authentication authentication) {
        return Token.builder()
                .accessToken(jwtTokenProvider.generateToken(authentication, valueConfig.getAccessTokenExpired()))
                .refreshToken(jwtTokenProvider.generateToken(authentication, valueConfig.getRefreshTokenExpired()))
                .build();
    }
}
