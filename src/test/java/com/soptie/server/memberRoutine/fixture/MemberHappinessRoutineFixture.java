package com.soptie.server.memberRoutine.fixture;

import com.soptie.server.memberRoutine.dto.MemberHappinessRoutinesResponse;

public class MemberHappinessRoutineFixture {

	private static final String CONTENT = "routine-content";
	private static final String DETAIL_CONTENT = "routine-detail-content";
	private static final String TIME_TAKEN = "5~10분 소요";
	private static final String PLACE = "루틴 장소";
	private static final String TITLE = "루틴 제목";
	private static final String NAME = "태마 이름";
	private static final String NAME_COLOR = "#ffcdc5";
	private static final String ICON_IMAGE_URL = "icon-image-url";
	private static final String CONTENT_IMAGE_URL = "content-image-url";

	public static MemberHappinessRoutinesResponse createMemberHappinessRoutinesResponseDTO() {
		return MemberHappinessRoutinesResponse.builder()
				.routineId(1L)
				.iconImageUrl(ICON_IMAGE_URL)
				.contentImageUrl(CONTENT_IMAGE_URL)
				.themeName(NAME)
				.themeNameColor(NAME_COLOR)
				.title(TITLE)
				.content(CONTENT)
				.detailContent(DETAIL_CONTENT)
				.place(PLACE)
				.timeTaken(TIME_TAKEN)
				.build();

	}
}
