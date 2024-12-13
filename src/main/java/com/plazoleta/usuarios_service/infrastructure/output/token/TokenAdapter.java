package com.plazoleta.usuarios_service.infrastructure.output.token;

import com.plazoleta.usuarios_service.infrastructure.Security.TokenUtils;
import com.plazoleta.usuarios_service.domain.spi.token.IToken;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class TokenAdapter implements IToken {

    @Override
    public String getBearerToken() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("Authorization");
    }

    @Override
    public String getEmail(String token) {
        if(token==(null)) throw  new IllegalArgumentException();
        return TokenUtils.getEmail(token.replace("Bearer ",""));
    }

    @Override
    public Long getUserAuthId(String token) {
        if(token==(null)) throw  new IllegalArgumentException();
        return TokenUtils.getUserAuthenticationId(token.replace("Bearer ",""));
    }

    @Override
    public String getUserAuthRol(String token) {
        if(token==(null)) throw  new IllegalArgumentException();
        return TokenUtils.getUserAuthenticationRol(token.replace("Bearer ",""));
    }
}
