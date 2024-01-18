package com.soptie.server.member.service;

import com.soptie.server.conversation.entity.Conversation;
import com.soptie.server.conversation.repository.ConversationRepository;
import com.soptie.server.member.dto.CottonCountResponse;
import com.soptie.server.member.dto.MemberHomeInfoResponse;
import com.soptie.server.member.dto.MemberProfileRequest;
import com.soptie.server.member.entity.CottonType;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.exception.MemberException;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberDoll.service.MemberDollService;
import com.soptie.server.memberRoutine.service.MemberDailyRoutineService;
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
    private final MemberDailyRoutineService memberDailyRoutineService;
    private final MemberRepository memberRepository;
    private final ConversationRepository conversationRepository;

    @Override
    @Transactional
    public void createMemberProfile(long memberId, MemberProfileRequest request) {
        val member = findMember(memberId);
        member.checkMemberDollNonExist();
        memberDailyRoutineService.createMemberDailyRoutines(member, request.routines());
        memberDollService.createMemberDoll(member, request.dollType(), request.name());
    }

    @Override
    @Transactional
    public CottonCountResponse giveCotton(long memberId, CottonType cottonType) {
        val member = findMember(memberId);
        val cottonCount = member.subtractAndGetCotton(cottonType);
        return CottonCountResponse.of(cottonCount);
    }

    @Override
    public MemberHomeInfoResponse getMemberHomeInfo(long memberId) {
        val member = findMember(memberId);
        member.checkMemberDollExist();
        val conversations = getConversations();
        return MemberHomeInfoResponse.of(member, conversations);
    }

    @Override
    public Member findMemberByRefreshToken(String refreshToken) {
        return memberRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
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
