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
    @Mapping(target = "rol", source = "rol", qualifiedByName = "mapRole")
    User toUser(UserRequestDto userRequestDto);


    @org.mapstruct.Named("mapRole")
    default Rol mapRole(Long rolId) {
        if (rolId == null) {
            return null;
        }
        Rol role = new Rol();
        role.setId(rolId);
        return role;
    }
}
