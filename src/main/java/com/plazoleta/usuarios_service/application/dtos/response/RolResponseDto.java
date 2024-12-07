package com.plazoleta.usuarios_service.application.dtos.response;

public class RolResponseDto {
    private String nameRol;
    private String descriptionRol;

    public String getNameRol() {
        return nameRol;
    }

    public void setNameRol(String nameRol) {
        this.nameRol = nameRol;
    }

    public String getDescriptionRol() {
        return descriptionRol;
    }

    public void setDescriptionRol(String descriptionRol) {
        this.descriptionRol = descriptionRol;
    }
}
