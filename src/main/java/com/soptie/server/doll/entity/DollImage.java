package com.soptie.server.doll.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DollImage {

	private String faceImageUrl;

	private String attentionImageUrl;

	private String frameImageUrl;
}
