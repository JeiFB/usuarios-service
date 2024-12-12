package com.plazoleta.usuarios_service.application.mapper;


import com.plazoleta.usuarios_service.application.dtos.response.RestaurantAndEmployeeResponseDto;
import com.plazoleta.usuarios_service.domain.models.RestaurantAndEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)

public interface IRestaurantAndEmployeeResponseMapper {
    RestaurantAndEmployeeResponseDto toResponse(RestaurantAndEmployee restaurantAndEmployee);

    List<RestaurantAndEmployeeResponseDto> toResponseList(List<RestaurantAndEmployee> restaurantAndEmployees);
}
