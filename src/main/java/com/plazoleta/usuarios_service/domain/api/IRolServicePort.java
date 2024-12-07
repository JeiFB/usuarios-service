package com.plazoleta.usuarios_service.domain.api;

import com.plazoleta.usuarios_service.domain.models.Rol;

import java.util.List;

public interface IRolServicePort {
    void saveRol(Rol rol);
    Rol getRolById(Long id);
    List<Rol>getAllRoles();
    void deleteRol(Long id);
}
