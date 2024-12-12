package com.plazoleta.usuarios_service.infrastructure.output.feignclients;

import com.plazoleta.usuarios_service.application.dtos.response.RestaurantResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(contextId = "restaurant", name = "plazoleta-service", url = "localhost:8080/api/v1/restaurant")
public interface RestaurantFeignClient {

    @GetMapping("/restaurantByIdPropietario/{id}")
    RestaurantResponseDto getRestaurantByOwnerId(@PathVariable(value = "id") Long idOwner);
}
