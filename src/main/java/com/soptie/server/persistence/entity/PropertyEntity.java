package com.soptie.server.persistence.entity;

import com.soptie.server.common.constants.DomainConstants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = DomainConstants.PROPERTY, schema = DomainConstants.SOFTIE)
public class PropertyEntity extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String key;

	@Column(nullable = false)
	private String value;
}
