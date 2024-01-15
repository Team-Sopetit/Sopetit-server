package com.soptie.server.member.service;

import com.soptie.server.conversation.entity.Conversation;
import com.soptie.server.conversation.repository.ConversationRepository;
import com.soptie.server.member.dto.MemberHomeInfoResponse;
import com.soptie.server.member.dto.MemberProfileRequest;
import com.soptie.server.member.entity.CottonType;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberDoll.service.MemberDollService;
import com.soptie.server.memberRoutine.service.MemberDailyRoutineService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.soptie.server.auth.message.ErrorMessage.INVALID_TOKEN;
import static com.soptie.server.member.message.ErrorMessage.*;

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
    public void createMemberProfile(Long memberId, MemberProfileRequest request) {
        val member = findMember(memberId);
        checkMemberProfileExist(member);
        memberDailyRoutineService.createMemberDailyRoutines(member, request.routines());
        memberDollService.createMemberDoll(member, request.dollType(), request.name());
    }

    @Override
    @Transactional
    public int giveCotton(Long memberId, CottonType cottonType) {
        val member = findMember(memberId);
        return giveCottonByCottonType(member, cottonType);
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

    public MemberHomeInfoResponse getMemberHomeInfo(Long memberId) {
        val member = findMember(memberId);
        val conversations = getConversations();
        return MemberHomeInfoResponse.of(member, conversations);
    }

    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_MEMBER.getMessage()));
    }

    private void checkMemberProfileExist(Member member) {
        if (Objects.nonNull(member.getMemberDoll())) {
            throw new IllegalStateException(EXIST_PROFILE.getMeesage());
        }
    }

    private void checkMemberCottonCount(int cottonCount) {
        if (cottonCount <= 0) {
            throw new IllegalStateException(NOT_ENOUGH_COTTON.getMessage());
        }
    }

    private List<String> getConversations() {
        return conversationRepository.findAll().stream()
                .map(Conversation::getContent)
                .toList();
    }
}
