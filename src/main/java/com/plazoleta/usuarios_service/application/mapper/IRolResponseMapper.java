package com.plazoleta.usuarios_service.application.mapper;

import com.plazoleta.usuarios_service.application.dtos.request.RolRequestDto;
import com.plazoleta.usuarios_service.application.dtos.response.RolResponseDto;
import com.plazoleta.usuarios_service.domain.models.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRolResponseMapper {
    RolResponseDto toRolDto(Rol rol);

    List<RolResponseDto> toRolList(List<Rol> roles);
}
