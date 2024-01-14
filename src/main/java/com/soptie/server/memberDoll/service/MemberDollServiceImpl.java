package com.soptie.server.memberDoll.service;

import com.soptie.server.doll.entity.Doll;
import com.soptie.server.doll.entity.DollType;
import com.soptie.server.doll.repository.DollRepository;
import com.soptie.server.doll.service.DollService;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberDoll.repository.MemberDollRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.soptie.server.doll.message.ErrorMessage.INVALID_TYPE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberDollServiceImpl implements MemberDollService {

    private final MemberDollRepository memberDollRepository;
    private final DollRepository dollRepository;

    @Override
    @Transactional
    public void createMemberDoll(Member member, DollType dollType, String name) {
        val doll = getDoll(dollType);
        val memberDoll = new MemberDoll(member, doll, name);
        memberDollRepository.save(memberDoll);
    }

    @Override
    @Transactional
    public void deleteByMember(Member member) {
        memberDollRepository.deleteByMember(member);
    }

    private Doll getDoll(DollType type) {
        return dollRepository.findByDollType(type)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_TYPE.getMessage()));
    }
}
