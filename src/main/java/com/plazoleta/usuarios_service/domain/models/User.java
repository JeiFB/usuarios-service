package com.plazoleta.usuarios_service.domain.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String name;
    private String lastName;
    private String numberCell;
    private String document;
    private LocalDate birtDate;
    private String email;
    private String password;
    private Rol rol;
}
