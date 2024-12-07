package com.plazoleta.usuarios_service.domain.usecase;

import com.plazoleta.usuarios_service.domain.api.IRolServicePort;
import com.plazoleta.usuarios_service.domain.models.Rol;
import com.plazoleta.usuarios_service.domain.spi.persistence.IRolPersistencePort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class RolUseCase implements IRolServicePort {

    private  final IRolPersistencePort rolPersistencePort;

    @Override
    public void saveRol(Rol rol) {
        rolPersistencePort.saveRol(rol);
    }

    @Override
    public Rol getRolById(Long id) {
       return rolPersistencePort.getRolById(id);
    }

    @Override
    public List<Rol> getAllRoles() {
        return rolPersistencePort.getAllRoles();
    }
    @Override
    public void deleteRol(Long id) {
        rolPersistencePort.deleteRol(id);
    }
}
