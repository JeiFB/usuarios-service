package com.plazoleta.usuarios_service.infrastructure.output.jpa.adapter;

import com.plazoleta.usuarios_service.domain.models.Rol;
import com.plazoleta.usuarios_service.domain.spi.persistence.IRolPersistencePort;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.entity.RolEntity;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.mapper.IRolEntityMapper;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.repository.IRolRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class RolJpaAdapter implements IRolPersistencePort {

    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;

    @Override
    public Rol getRolById(Long id) {
        Optional<RolEntity> optionalRolEntity = rolRepository.findById(id);
        RolEntity rolEntity = optionalRolEntity.orElse(null);
        return rolEntityMapper.toRol(rolEntity);
    }

    @Override
    public List<Rol> getAllRoles() {

        return rolEntityMapper.toRolList(rolRepository.findAll());
    }

    @Override
    public void saveRol(Rol rol) {
        rolEntityMapper.toRol(rolRepository.save(rolEntityMapper.toRolEntity(rol)));
    }

    @Override
    public void deleteRol(Long id) {

    }
}
