package com.soptie.server.support;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.soptie.server.common.config.JpaAuditingConfig;
import com.soptie.server.common.config.JpaQueryFactoryConfig;

@DataJpaTest(showSql = false)
@Import({JpaAuditingConfig.class, JpaQueryFactoryConfig.class})
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RepositoryTest {
}
