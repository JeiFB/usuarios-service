package com.plazoleta.usuarios_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rol {
    private Long id;
    private  String nameRol;
    private  String descriptionRol;
}
