package com.soptie.server.base;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.soptie.server.config.TestJpaQueryFactoryConfig;

@Import(TestJpaQueryFactoryConfig.class)
@DataJpaTest
public abstract class BaseRepositoryTest {
}
