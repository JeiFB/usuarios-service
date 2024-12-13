package com.plazoleta.usuarios_service.infrastructure.output.jpa.mapper;

import com.plazoleta.usuarios_service.domain.models.Rol;
import com.plazoleta.usuarios_service.domain.models.User;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IUserEntityMapper {

    @Mapping(target = "rol.id", source = "rol.id")
    UserEntity toEntity(User user);

    @Mapping(target = "rol.id", source = "rol.id")
    User toUser(UserEntity userEntity);
}
