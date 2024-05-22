package com.soptie.server.doll.adapter;

import com.soptie.server.common.support.RepositoryAdapter;
import com.soptie.server.doll.entity.Doll;
import com.soptie.server.doll.entity.DollType;
import com.soptie.server.doll.exception.DollException;
import com.soptie.server.doll.repository.DollRepository;
import lombok.RequiredArgsConstructor;

import static com.soptie.server.doll.message.ErrorCode.INVALID_TYPE;

@RepositoryAdapter
@RequiredArgsConstructor
public class DollFinder {

    private final DollRepository dollRepository;

    public Doll findByType(DollType type) {
        return dollRepository.findByDollType(type)
                .orElseThrow(() -> new DollException(INVALID_TYPE));
    }
}
