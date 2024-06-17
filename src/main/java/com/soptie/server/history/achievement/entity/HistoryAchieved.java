package com.soptie.server.history.achievement.entity;

import java.time.LocalDateTime;

import com.soptie.server.memberRoutine.entity.MemberRoutine;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class HistoryAchieved {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	private LocalDateTime achievedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "routine_id")
	private MemberRoutine achievedRoutine;

	public HistoryAchieved(MemberRoutine routine) {
		this.achievedRoutine = routine;
		this.achievedAt = LocalDateTime.now();
	}
}
