package com.soptie.server.domain.test;

import java.time.Duration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.soptie.server.api.web.jwt.JwtTokenProvider;
import com.soptie.server.api.web.jwt.UserAuthentication;
import com.soptie.server.domain.member.Member;
import com.soptie.server.domain.member.SocialType;
import com.soptie.server.persistence.adapter.MemberAdapter;

import lombok.RequiredArgsConstructor;

@Profile("dev")
@Service
@RequiredArgsConstructor
public class TestService {

	private final MemberAdapter memberAdapter;
	private final JwtTokenProvider jwtTokenProvider;
	private final BCryptPasswordEncoder passwordEncoder;

	public static final String TOKEN_TYPE_BEARER = "Bearer ";

	@Value("${test.id}")
	private String encodedTestId;

	public String getTestAccessToken(String testId) {
		String socialId = testId.replaceAll(TOKEN_TYPE_BEARER, StringUtils.EMPTY);

		if (!passwordEncoder.matches(socialId, encodedTestId)) {
			return null;
		}

		Member member = memberAdapter.findBySocialTypeAndSocialId(SocialType.ETC, socialId)
			.orElseGet(() -> memberAdapter.save(new Member(SocialType.ETC, socialId)));

		return jwtTokenProvider.generateToken(
			new UserAuthentication(member.getId(), null, null),
			Duration.ofHours(1).toMillis());
	}
}
