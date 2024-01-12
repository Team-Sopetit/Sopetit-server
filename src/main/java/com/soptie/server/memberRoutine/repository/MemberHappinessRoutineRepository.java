package com.soptie.server.memberRoutine.repository;

import com.soptie.server.memberRoutine.entity.happiness.MemberHappinessRoutine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberHappinessRoutineRepository extends JpaRepository<MemberHappinessRoutine, Long>, MemberHappinessRoutineCustomRepository{
}
