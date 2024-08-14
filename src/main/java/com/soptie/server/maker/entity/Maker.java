package com.soptie.server.maker.entity;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

import java.util.List;

import com.soptie.server.common.support.StringListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
public class Maker {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "maker_id")
	private Long id;

	@Column(nullable = false)
	private String name;

	private String job;

	private String profileImageUrl;

	private String content;

	@Column(nullable = false)
	private long themeId;

	@Convert(converter = StringListConverter.class)
	private List<String> tags;
}
