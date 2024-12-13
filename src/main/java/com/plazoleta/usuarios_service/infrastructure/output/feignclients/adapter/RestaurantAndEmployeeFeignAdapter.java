package com.plazoleta.usuarios_service.infrastructure.output.feignclients.adapter;

import com.plazoleta.usuarios_service.application.dtos.request.RestaurantAndEmployeeRequestDto;
import com.plazoleta.usuarios_service.application.mapper.IRestaurantAndEmployeeRequestMapper;
import com.plazoleta.usuarios_service.domain.models.RestaurantAndEmployee;
import com.plazoleta.usuarios_service.domain.spi.feignclients.IRestaurantAndEmployeeFeignClientPort;
import com.plazoleta.usuarios_service.infrastructure.output.feignclients.RestaurantAndEmployeeFeignClient;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantAndEmployeeFeignAdapter implements IRestaurantAndEmployeeFeignClientPort {
    private final RestaurantAndEmployeeFeignClient restaurantAndEmployeeFeignClient;
    private final IRestaurantAndEmployeeRequestMapper restaurantAndEmployeeRequestMapper;

    @Override
    public void saveEmployeeInRestaurant(RestaurantAndEmployee employee) {
        RestaurantAndEmployeeRequestDto restaurantEmployeeRequestDto = restaurantAndEmployeeRequestMapper.toRequest(employee);
        restaurantAndEmployeeFeignClient.saveRestaurantEmployee(restaurantEmployeeRequestDto);
    }
}
