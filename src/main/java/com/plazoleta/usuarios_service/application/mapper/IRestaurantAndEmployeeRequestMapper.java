package com.plazoleta.usuarios_service.application.mapper;

import com.plazoleta.usuarios_service.application.dtos.request.RestaurantAndEmployeeRequestDto;
import com.plazoleta.usuarios_service.domain.models.RestaurantAndEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IRestaurantAndEmployeeRequestMapper {
    RestaurantAndEmployee toRestaurantAndEmployee(RestaurantAndEmployeeRequestDto restaurantAndEmployeeRequestDto);

    RestaurantAndEmployeeRequestDto toRequest(RestaurantAndEmployee restaurantAndEmployee);
}
