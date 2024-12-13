package com.plazoleta.usuarios_service.application.dtos.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RolResponseDto {
    private Long id;
    private String nameRol;
    private String descriptionRol;
}
