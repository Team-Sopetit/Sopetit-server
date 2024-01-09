package com.soptie.server.doll.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class DollImage {

	private String faceImageUrl;

	private String attentionImageUrl;

	private String frameImageUrl;
}
