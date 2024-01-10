package com.soptie.server.auth.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.soptie.server.common.config.ValueConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class KakaoService {

    private final ValueConfig valueConfig;

    protected String getKakaoData(String socialAccessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", socialAccessToken);
        HttpEntity<JsonArray> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<Object> responseData = restTemplate.postForEntity(valueConfig.getUserInfoUri(), httpEntity, Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(responseData.getBody(), Map.class).get("id").toString();
    }
}
