package com.soptie.server.domain.auth;

import static com.soptie.server.common.message.MemberErrorCode.*;

import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.web.jwt.JwtTokenProvider;
import com.soptie.server.api.web.jwt.UserAuthentication;
import com.soptie.server.common.support.ValueConfig;
import com.soptie.server.domain.usecase.AuthService;
import com.soptie.server.external.AppleService;
import com.soptie.server.external.KakaoService;
import com.soptie.server.persistence.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthServiceImpl implements AuthService {

	private final JwtTokenProvider jwtTokenProvider;
	private final MemberRepository memberRepository;
	private final KakaoService kakaoService;
	private final AppleService appleService;
	private final MemberService memberService;
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
		val member = Member.builder().socialType(socialType).socialId(socialId).build();
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
		return memberRepository.findById(id).orElseThrow(() -> new MemberException(INVALID_MEMBER));
	}

	private Member findMember(String refreshToken) {
		return memberRepository.findByRefreshToken(getTokenFromBearerString(refreshToken))
			.orElseThrow(() -> new MemberException(INVALID_MEMBER));
	}

	private String getTokenFromBearerString(String token) {
		return token.replaceFirst(ValueConfig.BEARER_HEADER, ValueConfig.BLANK);
	}

	private String generateAccessToken(long memberId) {
		val authentication = new UserAuthentication(memberId, null, null);
		return jwtTokenProvider.generateToken(authentication, valueConfig.getAccessTokenExpired());
	}

	private void deleteMemberDoll(MemberDoll memberDoll) {
		if (Objects.nonNull(memberDoll)) {
			memberDollService.deleteMemberDoll(memberDoll); //TODO: using adapter
		}
	}

	private void deleteMember(Member member) {
		memberService.deleteMember(member);
	}
}
