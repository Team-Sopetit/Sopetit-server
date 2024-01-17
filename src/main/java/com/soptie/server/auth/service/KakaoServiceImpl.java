package com.soptie.server.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.soptie.server.common.config.ValueConfig;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoServiceImpl implements KakaoService {

    private final ValueConfig valueConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public String getKakaoData(String socialAccessToken) {
        val headers = new HttpHeaders();
        headers.add("Authorization", socialAccessToken);
        val httpEntity = new HttpEntity<JsonArray>(headers);
        val responseData = restTemplate.postForEntity(valueConfig.getKakaoUri(), httpEntity, Object.class);
        return objectMapper.convertValue(responseData.getBody(), Map.class).get("id").toString();
    }
}
