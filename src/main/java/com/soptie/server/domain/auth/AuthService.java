package com.soptie.server.domain.auth;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.auth.dto.SignInRequest;
import com.soptie.server.api.controller.auth.dto.SignInResponse;
import com.soptie.server.api.controller.auth.dto.TokenGetResponse;
import com.soptie.server.api.web.jwt.JwtTokenProvider;
import com.soptie.server.api.web.jwt.UserAuthentication;
import com.soptie.server.common.support.ValueConfig;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.member.SocialType;
import com.soptie.server.external.oauth.AppleService;
import com.soptie.server.external.oauth.KakaoService;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.MemberDollAdapter;
import com.soptie.server.persistence.adapter.challenge.MemberChallengeAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

	private final JwtTokenProvider jwtTokenProvider;
	private final KakaoService kakaoService;
	private final AppleService appleService;
	private final ValueConfig valueConfig;

	private final MemberDollAdapter memberDollAdapter;
	private final MemberRoutineAdapter memberRoutineAdapter;
	private final MemberChallengeAdapter memberChallengeAdapter;
	private final MemberAdapter memberAdapter;

	@Transactional
	public SignInResponse signIn(String socialAccessToken, SignInRequest request) {
		val member = getMember(socialAccessToken, request.socialType());
		val token = getToken(member);
		val isMemberDollExist = memberDollAdapter.isExistByMember(member.getId());
		memberAdapter.update(member);
		return SignInResponse.of(token, isMemberDollExist);
	}

	public TokenGetResponse reissueToken(String refreshToken) {
		val member = memberAdapter.findByRefreshToken(getTokenFromBearerString(refreshToken));
		val token = generateAccessToken(member.getId());
		return TokenGetResponse.from(token);
	}

	@Transactional
	public void signOut(long memberId) {
		val member = memberAdapter.findById(memberId);
		member.setRefreshToken(null);
		memberAdapter.update(member);
	}

	@Transactional
	public void withdraw(long memberId) {
		memberAdapter.findById(memberId);
		memberRoutineAdapter.deleteAllByMemberId(memberId);
		memberChallengeAdapter.deleteAllByMemberId(memberId);
		memberDollAdapter.deleteByMember(memberId);
		memberAdapter.delete(memberId);
	}

	private Member getMember(String socialAccessToken, SocialType socialType) {
		val socialId = getSocialId(socialAccessToken, socialType);
		return signUp(socialType, socialId);
	}

	private String getSocialId(String socialAccessToken, SocialType socialType) {
		return switch (socialType) {
			case APPLE -> appleService.getAppleData(socialAccessToken);
			case KAKAO -> kakaoService.getKakaoData(socialAccessToken);
			default -> null;
		};
	}

	private Member signUp(SocialType socialType, String socialId) {
		return memberAdapter.findBySocialTypeAndSocialId(socialType, socialId)
			.orElseGet(() -> memberAdapter.save(new Member(socialType, socialId)));
	}

	private Token getToken(Member member) {
		val token = generateToken(new UserAuthentication(member.getId(), null, null));
		member.setRefreshToken(token.getRefreshToken());
		return token;
	}

	private Token generateToken(Authentication authentication) {
		return Token.builder()
			.accessToken(jwtTokenProvider.generateToken(authentication, valueConfig.getAccessTokenExpired()))
			.refreshToken(jwtTokenProvider.generateToken(authentication, valueConfig.getRefreshTokenExpired()))
			.build();
	}

	private String getTokenFromBearerString(String token) {
		return token.replaceFirst(ValueConfig.BEARER_HEADER, ValueConfig.BLANK);
	}

	private String generateAccessToken(long memberId) {
		val authentication = new UserAuthentication(memberId, null, null);
		return jwtTokenProvider.generateToken(authentication, valueConfig.getAccessTokenExpired());
	}
}
