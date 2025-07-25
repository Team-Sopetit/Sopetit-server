package com.soptie.server.persistence.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soptie.server.persistence.entity.PropertyEntity;

public interface PropertyRepository extends JpaRepository<PropertyEntity, Long> {
	Optional<PropertyEntity> findByKey(String key);

	List<PropertyEntity> findByKeyIn(List<String> keys);
}
