package com.muhammet.java15_x.utility;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtManager {
    /**
     * Token oluşturmak için gerekli parametreler
     * SecretKey -> token imzalamak için gerekli şifre
     * Issuer -> jwt token sahibine ait bilgi
     * IssuerAt -> token üretilme zamanı
     * ExpiresAt -> token geçerlilik son zamanı, bitiş anı
     * Claim -> içerisinde KEY-VALUE şeklinde değer saklayan nesneler.
     * Sign -> imzalama için kullanılır mutlaka bir şifreleme algoritması vermek gerekir.
     */
    private String SecretKey;
    private final String Issuer= "Java15 X";
    private final Long ExDate = 1000L * 40; // 40sn sonra iptal olsun

    public Optional<String> createToken(Long authId){
        return Optional.empty();
    }

    public Optional<Long> validateToken(String token){
        return Optional.empty();
    }
}
