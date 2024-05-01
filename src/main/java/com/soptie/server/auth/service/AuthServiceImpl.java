package com.soptie.server.auth.service;

import com.soptie.server.auth.service.dto.request.SignInServiceRequest;
import com.soptie.server.auth.service.dto.request.TokenGetServiceRequest;
import com.soptie.server.auth.service.dto.response.SignInServiceResponse;
import com.soptie.server.auth.service.dto.response.TokenGetServiceResponse;
import com.soptie.server.auth.jwt.JwtTokenProvider;
import com.soptie.server.auth.jwt.UserAuthentication;
import com.soptie.server.auth.vo.Token;
import com.soptie.server.common.config.ValueConfig;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.entity.SocialType;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.member.service.MemberService;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberDoll.service.MemberDollService;
import com.soptie.server.memberRoutine.adapter.MemberRoutineDeleter;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;
import com.soptie.server.memberRoutine.service.MemberHappinessRoutineService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static com.soptie.server.member.message.ErrorCode.INVALID_MEMBER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final KakaoService kakaoService;
    private final AppleService appleService;
    private final MemberService memberService;
    private final MemberHappinessRoutineService memberHappinessRoutineService;
    private final MemberDollService memberDollService;
    private final ValueConfig valueConfig;

    private final MemberRoutineDeleter memberRoutineDeleter;

    @Override
    @Transactional
    public SignInServiceResponse signIn(SignInServiceRequest request) {
        val member = getMember(request.socialAccessToken(), request.socialType());
        val token = getToken(member);
        val isMemberDollExist = member.isMemberDollExist();
        return SignInServiceResponse.of(token, isMemberDollExist);
    }

    @Override
    public TokenGetServiceResponse reissueToken(TokenGetServiceRequest request) {
        val member = findMember(request.refreshToken());
        val token = generateAccessToken(member.getId());
        return TokenGetServiceResponse.of(token);
    }

    @Override
    @Transactional
    public void signOut(long memberId) {
        val member = findMember(memberId);
        member.resetRefreshToken();
    }

    @Override
    @Transactional
    public void withdraw(long memberId) {
        val member = findMember(memberId);
        deleteMemberDoll(member.getMemberDoll());
        memberRoutineDeleter.deleteByMember(member);
        deleteMemberHappinessRoutine(member.getHappinessRoutine());
        deleteMember(member);
    }

    private Member getMember(String socialAccessToken, SocialType socialType) {
        val socialId = getSocialId(socialAccessToken, socialType);
        return signUp(socialType, socialId);
    }

    private String getSocialId(String socialAccessToken, SocialType socialType) {
        return switch (socialType) {
            case APPLE -> appleService.getAppleData(socialAccessToken);
            case KAKAO -> kakaoService.getKakaoData(socialAccessToken);
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

    private Member findMember(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private Member findMember(String refreshToken) {
        return memberRepository.findByRefreshToken(getTokenFromBearerString(refreshToken))
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private String getTokenFromBearerString(String token) {
        return token.replaceFirst(valueConfig.getBEARER_HEADER(), valueConfig.getBLANK());
    }

    private String generateAccessToken(long memberId) {
        val authentication = new UserAuthentication(memberId, null, null);
        return jwtTokenProvider.generateToken(authentication, valueConfig.getAccessTokenExpired());
    }

    private void deleteMemberDoll(MemberDoll memberDoll) {
        if (Objects.nonNull(memberDoll)) {
            memberDollService.deleteMemberDoll(memberDoll);
        }
    }

    private void deleteMemberHappinessRoutine(MemberHappinessRoutine memberHappinessRoutine) {
        if (Objects.nonNull(memberHappinessRoutine)) {
            memberHappinessRoutineService.deleteMemberHappinessRoutine(memberHappinessRoutine);
        }
    }

    private void deleteMember(Member member) {
        memberService.deleteMember(member);
    }
}
