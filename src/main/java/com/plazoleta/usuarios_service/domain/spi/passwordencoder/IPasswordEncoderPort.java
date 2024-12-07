package com.plazoleta.usuarios_service.domain.spi.passwordencoder;

public interface IPasswordEncoderPort {
    String encode(String password);
}
