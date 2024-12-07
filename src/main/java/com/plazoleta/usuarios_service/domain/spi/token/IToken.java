package com.plazoleta.usuarios_service.domain.spi.token;

public interface IToken {
    String getBearerToken();
    String getEmail(String token);
    Long getUserAuthId(String token);
    String getUserAuthRol(String token);
}
