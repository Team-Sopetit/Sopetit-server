package com.soptie.server.memberDoll.service;

import com.soptie.server.doll.entity.Doll;
import com.soptie.server.doll.entity.DollType;
import com.soptie.server.doll.exception.DollException;
import com.soptie.server.doll.repository.DollRepository;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberDoll.repository.MemberDollRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.soptie.server.doll.message.ErrorCode.INVALID_TYPE;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberDollServiceImpl implements MemberDollService {

    private final MemberDollRepository memberDollRepository;
    private final DollRepository dollRepository;

    @Override
    @Transactional
    public void createMemberDoll(Member member, DollType dollType, String name) {
        val doll = findDoll(dollType);
        val memberDoll = new MemberDoll(member, doll, name);
        memberDollRepository.save(memberDoll);
    }

    @Override
    @Transactional
    public void deleteMemberDoll(MemberDoll memberDoll) {
        memberDollRepository.delete(memberDoll);
    }

    private Doll findDoll(DollType type) {
        return dollRepository.findByDollType(type)
                .orElseThrow(() -> new DollException(INVALID_TYPE));
    }
}
