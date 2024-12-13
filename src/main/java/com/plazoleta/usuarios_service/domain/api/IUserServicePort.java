package com.plazoleta.usuarios_service.domain.api;

import com.plazoleta.usuarios_service.domain.models.User;

public interface IUserServicePort {
    //methods for our service
    void saveUser(User user);
    Boolean existsUserById(Long id);
    void saveRestaurantAndEmployee(User user);
    User getUserById(Long userId);
    User getUserByEmail(String email);
}
