package com.plazoleta.usuarios_service.infrastructure.Security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}
