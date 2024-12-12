package com.plazoleta.usuarios_service.application.mapper;

import com.plazoleta.usuarios_service.application.dtos.response.RestaurantResponseDto;
import com.plazoleta.usuarios_service.domain.models.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IRestaurantResponseMapper {
    RestaurantResponseDto toResponse(Restaurant restaurantModel);

    Restaurant toRestaurantModel(RestaurantResponseDto restaurantResponseDto);
}
