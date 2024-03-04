package com.soptie.server.member.service;

import com.soptie.server.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MemberServiceImplTest {

    @Mock
    private Member member;

    @Test
    @DisplayName("솜뭉치 개수가 양수일 때 솜뭉치를 줄 수 있다.")
    void canGiveCottonWhenCottonCountIsPositive() {
        // given
        member.addDailyCotton();

        // when

    }

    @Test
    @DisplayName("솜뭉치 개수가 0일 때 솜뭉치를 주려 하면 예외가 발생한다.")
    void occurExceptionGiveCottonWhenCottonCountIsZero() {

    }
}
