package com.plazoleta.usuarios_service.domain.spi.feignclients;

import com.plazoleta.usuarios_service.domain.models.Restaurant;

public interface IRestaurantFeingClientPort {
    Restaurant getRestaurantByOwnerId(Long ownerId);
}
