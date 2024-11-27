package com.soptie.server.persistence.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.persistence.repository.MemoRepository;

import lombok.RequiredArgsConstructor;

@RepositoryAdapter
@RequiredArgsConstructor
public class MemoAdapter {

	private final MemoRepository memoRepository;
}
