package com.plazoleta.usuarios_service.application.dtos.response;

import com.plazoleta.usuarios_service.domain.models.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserResponseDto {
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
