package com.plazoleta.usuarios_service.application.dtos.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponseDto {

    private String name;

    private String lastName;

    private String numberCell;
    private int document;
    private LocalDate birtDate;
    private String email;
    private String password;
    private long rol;

}
