package com.soptie.server.persistence.adapter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import com.soptie.server.persistence.entity.PropertyEntity;
import com.soptie.server.persistence.repository.PropertyRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PropertyAdapter {

	private final PropertyRepository propertyRepository;

	public Map<String, String> getByKeys(List<String> keys) {
		return propertyRepository.findByKeyIn(keys)
			.stream()
			.collect(Collectors.toMap(
				PropertyEntity::getKey,
				PropertyEntity::getValue
			));
	}

	public Pair<String, String> get(String key) {
		return propertyRepository.findByKey(key)
			.map(property -> Pair.of(property.getKey(), property.getValue()))
			.orElse(null);
	}
}
