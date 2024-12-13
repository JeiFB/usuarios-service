package com.plazoleta.usuarios_service.application.mapper;

import com.plazoleta.usuarios_service.domain.models.Rol;
import  com.plazoleta.usuarios_service.domain.models.User;
import com.plazoleta.usuarios_service.application.dtos.request.UserRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserRequestMapper {
    @Mapping(target = "rol.id", source = "rol")
    User toUser(UserRequestDto userRequestDto);

}
