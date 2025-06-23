package com.soptie.server.common.utils;

import java.util.Map;

public class MapUtils {

	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return map == null || map.isEmpty();
	}

	public static <K, V> boolean isNotEmpty(Map<K, V> map) {
		return !isEmpty(map);
	}
}
