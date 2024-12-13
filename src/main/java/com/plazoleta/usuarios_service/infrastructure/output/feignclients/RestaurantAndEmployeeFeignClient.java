package com.plazoleta.usuarios_service.infrastructure.output.feignclients;

import com.plazoleta.usuarios_service.application.dtos.request.RestaurantAndEmployeeRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(contextId = "restaurantAndEmployee", name = "plazoleta-service", url = "localhost:8080/api/v1/restaurantEmployee")
public interface RestaurantAndEmployeeFeignClient {

    @PostMapping("/")
    void saveRestaurantEmployee(RestaurantAndEmployeeRequestDto restaurantEmployeeRequestDto);
}
