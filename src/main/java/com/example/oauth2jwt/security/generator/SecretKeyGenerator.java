package com.example.oauth2jwt.security.generator;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;

@Slf4j
public class SecretKeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); //or HS384 or HS512
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        log.info("키: " + secretString);
    }
}
