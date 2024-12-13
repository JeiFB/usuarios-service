package com.plazoleta.usuarios_service.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {
    private Long id;
    private String name;
    private String nit;
    private String address;
    private String cellNumber;
    private String urlLogo;
    private Long idOwner;
}
