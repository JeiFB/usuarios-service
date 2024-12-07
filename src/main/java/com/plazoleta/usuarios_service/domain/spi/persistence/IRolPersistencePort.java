package com.plazoleta.usuarios_service.domain.spi.persistence;

import com.plazoleta.usuarios_service.domain.models.Rol;

import java.util.List;

public interface IRolPersistencePort {


    Rol getRolById(Long id);
    List<Rol> getAllRoles();
    void saveRol(Rol rol);
    void  deleteRol(Long id);

}
