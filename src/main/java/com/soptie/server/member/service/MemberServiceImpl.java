package com.soptie.server.member.service;

import com.soptie.server.conversation.entity.Conversation;
import com.soptie.server.conversation.repository.ConversationRepository;
import com.soptie.server.member.service.dto.request.CottonGiveServiceRequest;
import com.soptie.server.member.service.dto.request.MemberHomeInfoGetServiceRequest;
import com.soptie.server.member.service.dto.response.MemberCottonCountGetServiceResponse;
import com.soptie.server.member.service.dto.response.MemberHomeInfoGetServiceResponse;
import com.soptie.server.member.service.dto.request.MemberProfileCreateServiceRequest;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberDoll.service.MemberDollService;
import com.soptie.server.memberRoutine.service.MemberRoutineCreateService;

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

    private final MemberDollService memberDollService;
    private final MemberRoutineCreateService memberRoutineCreateService;
    private final MemberRepository memberRepository;
    private final ConversationRepository conversationRepository;

    @Override
    @Transactional
    public void createMemberProfile(MemberProfileCreateServiceRequest request) {
        val member = findMember(request.memberId());
        member.checkMemberDollNonExist();
        memberRoutineCreateService.createDailyRoutines(member, request.routines());
        memberDollService.createMemberDoll(member, request.dollType(), request.name());
    }

    @Override
    @Transactional
    public MemberCottonCountGetServiceResponse giveCotton(CottonGiveServiceRequest request) {
        val member = findMember(request.memberId());
        val cottonCount = member.subtractAndGetCotton(request.cottonType());
        return MemberCottonCountGetServiceResponse.of(cottonCount);
    }

    @Override
    public MemberHomeInfoGetServiceResponse getMemberHomeInfo(MemberHomeInfoGetServiceRequest request) {
        val member = findMember(request.memberId());
        member.checkMemberDollExist();
        val conversations = getConversations();
        return MemberHomeInfoGetServiceResponse.of(member, conversations);
    }

    @Override
    public void deleteMember(Member member) {
        memberRepository.delete(member);
    }

    private Member findMember(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private List<String> getConversations() {
        return conversationRepository.findAll().stream()
                .map(Conversation::getContent)
                .toList();
    }
}
