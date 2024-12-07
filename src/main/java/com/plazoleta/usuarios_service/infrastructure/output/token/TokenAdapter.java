package com.plazoleta.usuarios_service.infrastructure.output.token;

import com.plazoleta.usuarios_service.domain.spi.token.Itoken;

public class TokenAdapter implements Itoken {

    @Override
    public String getBearerToken() {
        return "";
    }

    @Override
    public String getEmail(String token) {
        return "";
    }

    @Override
    public Long getUserAuthId(String token) {
        return 0L;
    }

    @Override
    public String getUserAuthRol(String token) {
        return "";
    }
}
