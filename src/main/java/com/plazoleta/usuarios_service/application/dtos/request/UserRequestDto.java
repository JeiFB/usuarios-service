package com.plazoleta.usuarios_service.application.dtos.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @NotBlank(message = "Nombre invalido")
    @Size(min = 2, message = "Nombre de minimo 2 caracteres")
    private String name;

    @NotBlank(message = "Apellido invalido")
    @Size(min = 2, message = "Apellido de minimo 2 caracteres")
    private String lastName;

    @NotBlank(message = "Numero de telefono invalido")
    @Pattern(regexp = "^\\+?\\d{1,12}$", message = "El número de celular debe contener máximo 13 caracteres y debe de llavar el simbolo '+")
    private String numberCell;


    @NotBlank(message = "Numero de documento invcalido")
    @Pattern(regexp = "\\d+", message = "El documentoDeIdentidad debe ser númerico")
    @Size(min = 8, message = "El documento de identidad debe tener al menos 8 digitos")
    private String document;

    @Past(message = "La fecha de nacimiento debe ser en el pasado")
    private LocalDate birtDate;

    @NotBlank(message = "Ingrese el correo electronico")
    @Email(message = "Correo electronico invalido")
    private String email;

    @NotBlank(message = "Digite la contraseña")
    private String password;

    private Long rol;
}