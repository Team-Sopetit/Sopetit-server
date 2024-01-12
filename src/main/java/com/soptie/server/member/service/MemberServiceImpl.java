package com.soptie.server.member.service;

import com.soptie.server.conversation.entity.Conversation;
import com.soptie.server.conversation.repository.ConversationRepository;
import com.soptie.server.doll.entity.Doll;
import com.soptie.server.member.dto.MemberHomeScreenResponse;
import com.soptie.server.member.dto.MemberProfileRequest;
import com.soptie.server.member.entity.Member;
import com.soptie.server.member.repository.MemberRepository;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberDoll.service.MemberDollService;
import com.soptie.server.memberRoutine.service.MemberDailyRoutineService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.soptie.server.member.message.ErrorMessage.EXIST_PROFILE;
import static com.soptie.server.member.message.ErrorMessage.INVALID_MEMBER;

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
    public MemberHomeScreenResponse showMemberHomeScreen(Long memberId) {
        val member = findMember(memberId);
        val memberDoll = member.getMemberDoll();
        val doll = memberDoll.getDoll();
        List<String> conversations = getConversations();
        return MemberHomeScreenResponse.of(memberDoll.getName(),
                doll.getDollType(),
                doll.getImageInfo().getAttentionImageUrl(),
                doll.getImageInfo().getFrameImageUrl(),
                member.getCottonInfo(),
                conversations);
    }

    private Member findMember(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_MEMBER.getMeesage()));
    }

    private void checkMemberProfileExist(Member member) {
        if (Objects.nonNull(member)) {
            throw new IllegalStateException(EXIST_PROFILE.getMeesage());
        }
    }

    private List<String> getConversations() {
        return conversationRepository.findAll().stream()
                .map(Conversation::getContent)
                .toList();
    }
}
