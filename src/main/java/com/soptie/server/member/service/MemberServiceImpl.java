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
import java.util.Objects;

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
        checkMemberProfileExist(member);
        memberDailyRoutineService.createMemberDailyRoutines(member, request.routines());
        memberDollService.createMemberDoll(member, request.dollType(), request.name());
    }

    private void checkMemberProfileExist(Member member) {
        if (Objects.nonNull(member.getMemberDoll())) {
            throw new MemberException(EXIST_PROFILE);
        }
    }

    @Override
    public boolean isMemberDollExist(Member member) {
        return Objects.nonNull(member.getMemberDoll());
    }

    @Override
    @Transactional
    public CottonCountResponse giveCotton(long memberId, CottonType cottonType) {
        val member = findMember(memberId);
        val cottonCount = giveCottonByCottonType(member, cottonType);
        return CottonCountResponse.of(cottonCount);
    }

    private int giveCottonByCottonType(Member member, CottonType cottonType) {
        return switch (cottonType) {
            case DAILY -> giveDailyCotton(member);
            case HAPPINESS -> giveHappinessCotton(member);
        };
    }

    private int giveDailyCotton(Member member) {
        checkMemberCottonCount(member.getCottonInfo().getDailyCottonCount());
        return member.subtractDailyCotton();
    }

    private int giveHappinessCotton(Member member) {
        checkMemberCottonCount(member.getCottonInfo().getHappinessCottonCount());
        member.getMemberDoll().addHappinessCottonCount();
        return member.subtractHappinessCotton();
    }

    public MemberHomeInfoResponse getMemberHomeInfo(long memberId) {
        val member = findMember(memberId);
        checkMemberDoll(member);
        val conversations = getConversations();
        return MemberHomeInfoResponse.of(member, conversations);
    }

    private Member findMember(long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(INVALID_MEMBER));
    }

    private void checkMemberDoll(Member member) {
        if (Objects.isNull(member.getMemberDoll())) {
            throw new MemberException(NOT_EXIST_DOLL);
        }
    }

    private void checkMemberCottonCount(int cottonCount) {
        if (cottonCount <= 0) {
            throw new MemberException(NOT_ENOUGH_COTTON);
        }
    }

    private List<String> getConversations() {
        return conversationRepository.findAll().stream()
                .map(Conversation::getContent)
                .toList();
    }
}
