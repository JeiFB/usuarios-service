package com.plazoleta.usuarios_service.domain.spi.feignclients;

import com.plazoleta.usuarios_service.domain.models.RestaurantAndEmployee;

public interface IRestaurantAndEmployeeFeignClientPort {
    void saveEmployeeInRestaurant(RestaurantAndEmployee employee);
}
