package com.soptie.server.persistence.global;

import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.soptie.server.config.support.GlobalData;
import com.soptie.server.persistence.entity.PropertyEntity;
import com.soptie.server.persistence.repository.PropertyRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@EnableScheduling
@RequiredArgsConstructor
public class PropertyStore {

	private final PropertyRepository propertyRepository;

	@Getter
	@GlobalData
	private volatile Map<String, String> properties = Maps.newHashMap();

	@PostConstruct
	@Scheduled(cron = "0 0 1 * * *")
	public void init() {
		this.properties = propertyRepository.findAll()
			.stream()
			.collect(Collectors.toMap(
				PropertyEntity::getKey,
				PropertyEntity::getValue
			));
	}

	public String get(String key) {
		return properties.get(key);
	}
}
