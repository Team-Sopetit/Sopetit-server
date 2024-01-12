package com.soptie.server.memberRoutine.entity.happiness;

import com.soptie.server.member.entity.Member;
import com.soptie.server.routine.entity.happiness.HappinessRoutine;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Getter
public class CompletedMemberHappinessRoutine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_routine_id")
    private Long id;

    private int achieveCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id")
    private HappinessRoutine routine;

    public CompletedMemberHappinessRoutine(MemberHappinessRoutine routine) {
        this.achieveCount = routine.getAchieveCount();
        setMember(routine);
        this.routine = routine.getRoutine();
    }

    private void setMember(MemberHappinessRoutine routine) {
        routine.getMember().getDailyRoutines().remove(routine);
        this.member = routine.getMember();
    }
}
