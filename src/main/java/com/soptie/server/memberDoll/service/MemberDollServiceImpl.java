package com.soptie.server.memberDoll.service;

import com.soptie.server.doll.entity.DollType;
import com.soptie.server.doll.service.DollService;
import com.soptie.server.member.entity.Member;
import com.soptie.server.memberDoll.entity.MemberDoll;
import com.soptie.server.memberDoll.repository.MemberDollRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberDollServiceImpl implements MemberDollService {

    private final DollService dollService;
    private final MemberDollRepository memberDollRepository;

    @Override
    @Transactional
    public void createMemberDoll(Member member, DollType dollType, String name) {
        val doll = dollService.getDoll(dollType);
        val memberDoll = new MemberDoll(member, doll, name);
        memberDollRepository.save(memberDoll);
    }
}
