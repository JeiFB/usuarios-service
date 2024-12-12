package com.plazoleta.usuarios_service.application.dtos.response;

import com.plazoleta.usuarios_service.domain.models.Rol;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String nit;
    private String address;
    private String cellNumber;
    private String urlLogo;
    private Long idOwner;
}
