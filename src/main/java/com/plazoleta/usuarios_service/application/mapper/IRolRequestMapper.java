package com.plazoleta.usuarios_service.application.mapper;


import com.plazoleta.usuarios_service.application.dtos.request.RolRequestDto;
import com.plazoleta.usuarios_service.domain.models.Rol;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRolRequestMapper {
    Rol toRol(RolRequestDto rolRequestDto);
}
