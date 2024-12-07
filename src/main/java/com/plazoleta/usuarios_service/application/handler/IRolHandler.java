package com.plazoleta.usuarios_service.application.handler;

import com.plazoleta.usuarios_service.application.dtos.request.RolRequestDto;
import com.plazoleta.usuarios_service.application.dtos.response.RolResponseDto;

import java.util.List;

public interface IRolHandler {
    void saveRol(RolRequestDto rolRequestDto);
    RolResponseDto getRolById(Long id);
    List<RolResponseDto> getAllRoles();
    void deleteRol(long id);
}
