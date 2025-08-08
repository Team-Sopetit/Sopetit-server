package com.soptie.server.persistence.entity.routine;

import com.soptie.server.domain.memberroutine.MemberRoutine;
import com.soptie.server.domain.memberroutine.RoutineHistory;
import com.soptie.server.persistence.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "routine_history", schema = "softie")
public class RoutineHistoryEntity extends BaseEntity {
	private long memberRoutineId;
	private long memberId;
	private Long routineId;
	private String content;
	//todo. themeId 추가 고려해보기. custom memberRoutine 이 삭제되었을 때 알아낼 방법이 없음.

	public RoutineHistoryEntity(final MemberRoutine memberRoutine) {
		this.memberRoutineId = memberRoutine.getId();
		this.memberId = memberRoutine.getMemberId();
		this.routineId = memberRoutine.getRoutineId();
		this.content = memberRoutine.getContent();
	}

	public RoutineHistory toDomain() {
		return RoutineHistory.builder()
			.id(this.id)
			.memberRoutineId(this.memberRoutineId)
			.memberId(this.memberId)
			.routineId(this.routineId)
			.content(this.content)
			.createdAt(this.createdAt)
			.build();
	}
}
