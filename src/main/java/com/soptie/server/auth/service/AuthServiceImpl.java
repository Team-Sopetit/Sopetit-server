package com.soptie.server.auth.service;

import com.soptie.server.auth.dto.SignInRequest;
import com.soptie.server.auth.dto.SignInResponse;
import com.soptie.server.auth.exception.AuthException;
import com.soptie.server.auth.jwt.JwtTokenProvider;
import com.soptie.server.auth.jwt.UserAuthentication;
import com.soptie.server.auth.vo.Token;
import com.soptie.server.common.config.ValueConfig;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.entity.SocialType;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberDoll.service.MemberDollService;
import com.soptie.server.memberRoutine.entity.daily.MemberDailyRoutine;
import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;
import com.soptie.server.memberRoutine.service.CompletedMemberDailyRoutineService;
import com.soptie.server.memberRoutine.service.MemberDailyRoutineService;
import com.soptie.server.memberRoutine.service.MemberHappinessRoutineService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.soptie.server.auth.message.ErrorCode.*;
import static com.soptie.server.member.message.ErrorCode.INVALID_MEMBER;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final KakaoService kakaoService;
    private final MemberDailyRoutineService memberDailyRoutineService;
    private final MemberHappinessRoutineService memberHappinessRoutineService;
    private final MemberDollService memberDollService;
    private final CompletedMemberDailyRoutineService completedMemberDailyRoutineService;
    private final ValueConfig valueConfig;

    @Override
    @Transactional
    public SignInResponse signIn(String socialAccessToken, SignInRequest request) {
        return SignInResponse.of(getToken(getMember(socialAccessToken, request)));
    }

    @Override
    @Transactional
    public void signOut(Long memberId) {
        val member = findMember(memberId);
        member.resetRefreshToken();
    }

    @Override
    @Transactional
    public void withdraw(Long memberId) {
        val member = findMember(memberId);
        deleteMemberDoll(member.getMemberDoll());
        deleteMemberDailyRoutines(member.getDailyRoutines());
        deleteMemberHappinessRoutine(member.getHappinessRoutine());
        deleteCompletedMemberDailyRoutines(member);
        deleteMember(member);
    }

    private Member getMember(String socialAccessToken, SignInRequest request) {
        val socialType = request.socialType();
        val socialId = getSocialId(socialAccessToken, socialType);
        return signUp(socialType, socialId);
    }

    private String getSocialId(String socialAccessToken, SocialType socialType) {
        return switch (socialType) {
            case KAKAO -> kakaoService.getKakaoData(socialAccessToken);
            default -> throw new AuthException(INVALID_TOKEN);
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

    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_MEMBER.getMessage()));
    }

    private void deleteMemberDoll(MemberDoll memberDoll) {
        if (Objects.nonNull(memberDoll)) {
            memberDollService.deleteMemberDoll(memberDoll);
        }
    }

    private void deleteMemberDailyRoutines(List<MemberDailyRoutine> memberDailyRoutines) {
        memberDailyRoutines
                .forEach(memberDailyRoutineService::deleteMemberDailyRoutine);
    }

    private void deleteMemberHappinessRoutine(MemberHappinessRoutine memberHappinessRoutine) {
        if (Objects.nonNull(memberHappinessRoutine)) {
            memberHappinessRoutineService.deleteMemberHappinessRoutine(memberHappinessRoutine);
        }
    }

    private void deleteCompletedMemberDailyRoutines(Member member) {
        completedMemberDailyRoutineService.deleteCompletedMemberDailyRoutines(member);
    }

    private void deleteMember(Member member) {
        memberRepository.delete(member);
    }
}
