package com.plazoleta.usuarios_service.infrastructure.output.passwordencoder;

import com.plazoleta.usuarios_service.domain.spi.passwordencoder.IPasswordEncoderPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BCryptPasswordEncoderAdapter implements IPasswordEncoderPort {


    private final PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();

    public BCryptPasswordEncoderAdapter() {
    }

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
