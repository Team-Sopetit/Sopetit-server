package com.soptie.server.common.support;

import java.net.URI;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class UrlGenerator {

	public static URI getURI(String path, long id) {
		return ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path(path + id)
				.buildAndExpand()
				.toUri();
	}
}
