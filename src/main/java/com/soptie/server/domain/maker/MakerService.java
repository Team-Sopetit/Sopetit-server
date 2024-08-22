package com.soptie.server.domain.maker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.soptie.server.api.controller.dto.response.maker.GetMakerListResponse;
import com.soptie.server.persistence.adapter.ThemeAdapter;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MakerService {

	private final ThemeAdapter themeAdapter;

	public GetMakerListResponse acquireAll() {
		val themes = themeAdapter.findAllWithMaker();
		return GetMakerListResponse.from(themes);
	}
}
