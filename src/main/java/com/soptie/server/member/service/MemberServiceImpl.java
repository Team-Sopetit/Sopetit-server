package com.soptie.server.member.service;

import com.soptie.server.conversation.adapter.ConversationFinder;
import com.soptie.server.conversation.entity.Conversation;
import com.soptie.server.conversation.repository.ConversationRepository;
import com.soptie.server.doll.adapter.DollFinder;
import com.soptie.server.doll.entity.DollType;
import com.soptie.server.member.adapter.MemberDeleter;
import com.soptie.server.member.adapter.MemberFinder;
import com.soptie.server.member.service.dto.request.CottonGiveServiceRequest;
import com.soptie.server.member.service.dto.request.MemberHomeInfoGetServiceRequest;
import com.soptie.server.member.service.dto.response.MemberCottonCountGetServiceResponse;
import com.soptie.server.member.service.dto.response.MemberHomeInfoGetServiceResponse;
import com.soptie.server.member.service.dto.request.MemberProfileCreateServiceRequest;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberDoll.adapter.MemberDollSaver;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberDoll.service.MemberDollService;
import com.soptie.server.memberRoutine.adapter.MemberRoutineSaver;
import com.soptie.server.memberRoutine.service.MemberRoutineCreateService;

import com.soptie.server.routine.adapter.RoutineFinder;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.soptie.server.member.message.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final ConversationFinder conversationFinder;
    private final MemberFinder memberFinder;
    private final MemberDeleter memberDeleter;
    private final DollFinder dollFinder;
    private final RoutineFinder routineFinder;
    private final MemberDollSaver memberDollSaver;
    private final MemberRoutineSaver memberRoutineSaver;

    @Override
    @Transactional
    public void createMemberProfile(MemberProfileCreateServiceRequest request) {
        val member = memberFinder.findById(request.memberId());
        member.checkMemberDollNonExist();
        createDailyRoutines(member, request.routines());
        createMemberDoll(member, request.dollType(), request.name());
    }

    @Override
    @Transactional
    public MemberCottonCountGetServiceResponse giveCotton(CottonGiveServiceRequest request) {
        val member = memberFinder.findById(request.memberId());
        val cottonCount = member.subtractAndGetCotton(request.cottonType());
        return MemberCottonCountGetServiceResponse.of(cottonCount);
    }

    @Override
    public MemberHomeInfoGetServiceResponse getMemberHomeInfo(MemberHomeInfoGetServiceRequest request) {
        val member = memberFinder.findById(request.memberId());
        member.checkMemberDollExist();
        val conversations = getConversations();
        return MemberHomeInfoGetServiceResponse.of(member, conversations);
    }

    @Override
    public void deleteMember(Member member) {
        memberDeleter.delete(member);
    }

    private void createDailyRoutines(Member member, List<Long> routineIds) {
        routineIds.forEach(id -> memberRoutineSaver.checkHasDeletedAndSave(member, routineFinder.findById(id)));
    }

    private void createMemberDoll(Member member, DollType dollType, String name) {
        val doll = dollFinder.findByType(dollType);
        val memberDoll = new MemberDoll(member, doll, name);
        memberDollSaver.save(memberDoll);
    }

    private List<String> getConversations() {
        return conversationFinder.findAll().stream()
                .map(Conversation::getContent)
                .toList();
    }
}
