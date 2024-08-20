package com.soptie.server.domain.member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.domain.doll.DollType;
import com.soptie.server.persistence.adapter.ConversationFinder;
import com.soptie.server.persistence.adapter.DollFinder;
import com.soptie.server.persistence.adapter.MemberAdapter;
import com.soptie.server.persistence.adapter.MemberDollSaver;
import com.soptie.server.persistence.adapter.RoutineAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
	private final MemberAdapter memberAdapter;

	private final ConversationFinder conversationFinder;
	private final DollFinder dollFinder;
	private final RoutineAdapter routineAdapter;
	private final MemberDollSaver memberDollSaver;
	private final MemberRoutineSaver memberRoutineSaver;

	@Transactional
	public void createMemberProfile(MemberProfileCreateServiceRequest request) {
		val member = memberAdapter.findById(request.memberId());
		//TODO: check on MemberDoll
		member.checkMemberDollNonExist();
		createDailyRoutines(member, request.routines());
		createMemberDoll(member, request.dollType(), request.name());
	}

	@Transactional
	public MemberCottonCountGetServiceResponse giveCotton(CottonGiveServiceRequest request) {
		val member = memberAdapter.findById(request.memberId());
		//TODO: subtract MemberCotton & add DollCotton
		val cottonCount = member.subtractAndGetCotton(request.cottonType());
		return MemberCottonCountGetServiceResponse.of(cottonCount);
	}

	public MemberHomeInfoGetServiceResponse getMemberHomeInfo(MemberHomeInfoGetServiceRequest request) {
		val member = memberAdapter.findById(request.memberId());
		//TODO: check on MemberDoll
		member.checkMemberDollExist();
		val conversations = getConversations();
		return MemberHomeInfoGetServiceResponse.of(member, conversations);
	}

	private void createDailyRoutines(Member member, List<Long> routineIds) {
		routineIds.forEach(id -> {
			val routine = routineAdapter.findById(id);
			memberRoutineSaver.checkHasDeletedAndSave(member, routine);
		});
	}

	private void createMemberDoll(Member member, DollType dollType, String name) {
		val doll = dollFinder.findByType(dollType);
		val memberDoll = new MemberDoll(member, doll, name);
		memberDollSaver.save(memberDoll);
	}

	private List<String> getConversations() {
		return conversationFinder.findAll().stream().map(Conversation::getContent).toList();
	}
}
