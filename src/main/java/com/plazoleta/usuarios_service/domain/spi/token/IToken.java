package com.plazoleta.usuarios_service.domain.spi.token;

public interface Itoken {
    String getBearerToken();
    String getEmail(String token);
    Long getUserAuthId(String token);
    String getUserAuthRol(String token);
}
