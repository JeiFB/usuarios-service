package com.plazoleta.usuarios_service.infrastructure.output.feignclients.adapter;

import com.plazoleta.usuarios_service.application.dtos.response.RestaurantResponseDto;
import com.plazoleta.usuarios_service.application.mapper.IRestaurantResponseMapper;
import com.plazoleta.usuarios_service.domain.models.Restaurant;
import com.plazoleta.usuarios_service.domain.spi.feignclients.IRestaurantFeingClientPort;
import com.plazoleta.usuarios_service.infrastructure.output.feignclients.IRestaurantFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantFeignAdapter implements IRestaurantFeingClientPort {

    private  final IRestaurantFeignClient IRestaurantFeignClient;
    private  final IRestaurantResponseMapper restaurantResponseMapper;
    @Override
    public Restaurant getRestaurantByOwnerId(Long ownerId) {
        RestaurantResponseDto restaurantResponseDto = IRestaurantFeignClient.getRestaurantByOwnerId(ownerId);
        return restaurantResponseMapper.toRestaurantModel(restaurantResponseDto);
    }
}
