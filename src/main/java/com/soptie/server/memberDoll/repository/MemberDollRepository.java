package com.soptie.server.memberDoll.repository;

import com.soptie.server.member.entity.Member;
import com.soptie.server.memberDoll.entity.MemberDoll;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberDollRepository extends JpaRepository<MemberDoll, Long> {
    void deleteByMember(Member member);
}
