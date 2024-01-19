package com.soptie.server.auth.service;

import com.google.gson.*;
import com.soptie.server.auth.exception.AuthException;
import com.soptie.server.common.config.ValueConfig;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Objects;

import static com.soptie.server.auth.message.ErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AppleServiceImpl implements AppleService {

    private static final String TOKEN_VALUE_DELIMITER = "\\.";
    private static final String MODULUS = "n";
    private static final String EXPONENT = "e";
    private static final String KID_HEADER_KEY = "kid";
    private static final String ALG_HEADER_KEY = "alg";
    private static final String RSA = "RSA";
    private static final String KEY = "keys";
    private static final String ID = "sub";
    private static final int QUOTES = 1;
    private static final int POSITIVE_NUMBER = 1;

    private final ValueConfig valueConfig;

    @Override
    public String getAppleData(String socialAccessToken) {
        val publicKeyList = getApplePublicKeys();
        val publicKey = makePublicKey(socialAccessToken, publicKeyList);

        val userInfo = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build()
                .parseClaimsJws(socialAccessToken.replaceFirst(BEARER_HEADER, BLANK))
                .getBody();

        val userInfoObject = (JsonObject) JsonParser.parseString(new Gson().toJson(userInfo));
        return userInfoObject.get(ID).getAsString();
    }

    private JsonArray getApplePublicKeys() {
        val connection = sendHttpRequest();
        val result = getHttpResponse(connection);
        val keys = (JsonObject) JsonParser.parseString(result.toString());
        return (JsonArray) keys.get(KEY);
    }

    private HttpURLConnection sendHttpRequest() {
        try {
            val url = new URL(valueConfig.getAppleUri());
            val connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.GET.name());
            return connection;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private StringBuilder getHttpResponse(HttpURLConnection connection) {
        try {
            val bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            return splitHttpResponse(bufferedReader);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private StringBuilder splitHttpResponse(BufferedReader bufferedReader) {
        try {
            val result = new StringBuilder();

            String line;
            while (Objects.nonNull(line = bufferedReader.readLine())) {
                result.append(line);
            }
            bufferedReader.close();

            return result;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private PublicKey makePublicKey(String accessToken, JsonArray publicKeyList) {
        val decodeArray = accessToken.split(TOKEN_VALUE_DELIMITER);
        val header = new String(Base64.getDecoder().decode(decodeArray[0].replaceFirst(BEARER_HEADER, BLANK)));

        val kid = ((JsonObject) JsonParser.parseString(header)).get(KID_HEADER_KEY);
        val alg = ((JsonObject) JsonParser.parseString(header)).get(ALG_HEADER_KEY);
        val matchingPublicKey = findMatchingPublicKey(publicKeyList, kid, alg);

        if (Objects.isNull(matchingPublicKey)) {
            throw new AuthException(INVALID_KEY);
        }

        return getPublicKey(matchingPublicKey);
    }

    private JsonObject findMatchingPublicKey(JsonArray publicKeyList, JsonElement kid, JsonElement alg) {
        for (JsonElement publicKey : publicKeyList) {
            val publicKeyObject = publicKey.getAsJsonObject();
            val publicKid = publicKeyObject.get(KID_HEADER_KEY);
            val publicAlg = publicKeyObject.get(ALG_HEADER_KEY);

            if (Objects.equals(kid, publicKid) && Objects.equals(alg, publicAlg)) {
                return publicKeyObject;
            }
        }

        return null;
    }

    private PublicKey getPublicKey(JsonObject object) {
        try {
            val modulus = object.get(MODULUS).toString();
            val exponent = object.get(EXPONENT).toString();

            val modulusBytes = Base64.getUrlDecoder().decode(modulus.substring(QUOTES, modulus.length() - QUOTES));
            val exponentBytes = Base64.getUrlDecoder().decode(exponent.substring(QUOTES, exponent.length() - QUOTES));

            val modulusValue = new BigInteger(POSITIVE_NUMBER, modulusBytes);
            val exponentValue = new BigInteger(POSITIVE_NUMBER, exponentBytes);

            val publicKeySpec = new RSAPublicKeySpec(modulusValue, exponentValue);
            val keyFactory = KeyFactory.getInstance(RSA);

            return keyFactory.generatePublic(publicKeySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException exception) {
            throw new AuthException(INVALID_KEY);
        }
    }
}
