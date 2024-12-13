package com.plazoleta.usuarios_service.application.mapper;

import com.plazoleta.usuarios_service.application.dtos.response.UserResponseDto;
import com.plazoleta.usuarios_service.domain.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    UserResponseDto toResponse(User user);

    List<UserResponseDto> toResponseList(List<User> userList);
}
