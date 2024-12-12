package com.plazoleta.usuarios_service.application.dtos.request;

import jakarta.validation.constraints.NotBlank;

public class RestaurantAndEmployeeRequestDto {
    @NotBlank(message = "El restaurante_id es requerido")
    private Long restaurantId;
    @NotBlank(message = "El empleado_id es requerido")
    private Long employeeId;
}
