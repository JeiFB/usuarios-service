package com.plazoleta.usuarios_service.application.handler.impl;

import com.plazoleta.usuarios_service.application.dtos.request.RolRequestDto;
import com.plazoleta.usuarios_service.application.dtos.response.RolResponseDto;
import com.plazoleta.usuarios_service.application.handler.IRolHandler;
import com.plazoleta.usuarios_service.application.mapper.IRolRequestMapper;
import com.plazoleta.usuarios_service.application.mapper.IRolResponseMapper;
import com.plazoleta.usuarios_service.domain.api.IRolServicePort;
import com.plazoleta.usuarios_service.domain.models.Rol;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RolHandlerImpl implements IRolHandler {

    private final IRolServicePort rolServicePort;
    private final IRolResponseMapper rolResponseMapper;
    private final IRolRequestMapper rolRequestMapper;

    public RolHandlerImpl(IRolServicePort rolServicePort, IRolResponseMapper rolResponseMapper, IRolRequestMapper requestMapper) {
        this.rolServicePort = rolServicePort;
        this.rolResponseMapper = rolResponseMapper;
        this.rolRequestMapper = requestMapper;
    }

    @Override
    public void saveRol(RolRequestDto rolRequestDto) {
        Rol rol = rolRequestMapper.toRol(rolRequestDto);
        rolServicePort.saveRol(rol);
    }

    @Override
    public RolResponseDto getRolById(Long id) {
        return rolResponseMapper.toRolDto(rolServicePort.getRolById(id));
    }

    @Override
    public List<RolResponseDto> getAllRoles() {
        List<Rol> roles = rolServicePort.getAllRoles();
        return rolResponseMapper.toRolList(roles);
    }

    @Override
    public void deleteRol(long id) {
        rolServicePort.deleteRol(id);
    }
}
