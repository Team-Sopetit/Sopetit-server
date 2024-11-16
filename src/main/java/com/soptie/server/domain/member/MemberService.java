package com.soptie.server.domain.member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.request.member.CreateProfileRequest;
import com.soptie.server.api.controller.dto.response.member.GetHomeInfoResponse;
import com.soptie.server.api.controller.dto.response.member.GiveMemberCottonResponse;
import com.soptie.server.common.exception.ExceptionCode;
import com.soptie.server.common.exception.SoftieException;
import com.soptie.server.domain.conversation.Conversation;
import com.soptie.server.domain.doll.DollType;
import com.soptie.server.domain.memberdoll.MemberDoll;
import com.soptie.server.persistence.adapter.ConversationAdapter;
import com.soptie.server.persistence.adapter.DollAdapter;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.MemberDollAdapter;
import com.soptie.server.persistence.adapter.routine.MemberRoutineAdapter;
import com.soptie.server.persistence.adapter.routine.RoutineAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	private final MemberAdapter memberAdapter;
	private final ConversationAdapter conversationAdapter;
	private final DollAdapter dollAdapter;
	private final RoutineAdapter routineAdapter;
	private final MemberDollAdapter memberDollAdapter;
	private final MemberRoutineAdapter memberRoutineAdapter;

	@Transactional
	public void createMemberProfile(long memberId, CreateProfileRequest request) {
		val member = memberAdapter.findById(memberId);
		createMemberDoll(memberId, request.dollType(), request.name());
		createMemberRoutines(member, request.routines());
	}

	@Transactional
	public GiveMemberCottonResponse giveCotton(long memberId, CottonType cottonType) {
		val member = memberAdapter.findById(memberId);

		if (member.getCottonInfo().getCottonCount(cottonType) <= 0) {
			throw new SoftieException(ExceptionCode.BAD_REQUEST, "솜뭉치가 없습니다.");
		}

		member.getCottonInfo().subtractCotton(cottonType);
		memberAdapter.update(member);

		val memberDoll = memberDollAdapter.findByMember(memberId);
		memberDoll.getCottonInfo().giveCotton(cottonType);
		memberDollAdapter.update(memberDoll);

		return GiveMemberCottonResponse.of(member, cottonType);
	}

	public GetHomeInfoResponse getMemberHomeInfo(long memberId) {
		val member = memberAdapter.findById(memberId);
		val memberDoll = memberDollAdapter.findByMember(memberId);
		val conversations = conversationAdapter.findAll().stream().map(Conversation::getContent).toList();
		return GetHomeInfoResponse.of(member, memberDoll, conversations);
	}

	private void createMemberRoutines(Member member, List<Long> routineIds) {
		val routines = routineAdapter.findByIds(routineIds);
		memberRoutineAdapter.saveAll(member, routines);
	}

	private void createMemberDoll(long memberId, DollType dollType, String name) {
		if (!memberDollAdapter.isExistByMember(memberId)) {
			val doll = dollAdapter.findByType(dollType);
			memberDollAdapter.save(new MemberDoll(name, dollType, memberId, doll.getId()));
		}
	}
}
