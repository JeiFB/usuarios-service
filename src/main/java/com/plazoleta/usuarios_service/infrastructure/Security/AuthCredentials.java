package com.plazoleta.usuarios_service.Security;

import lombok.Data;

@Data
public class AuthCredentials {
    private String email;
    private String password;
}
