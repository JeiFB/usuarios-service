package com.plazoleta.usuarios_service.application.handler;

import com.plazoleta.usuarios_service.application.dtos.request.UserRequestDto;
import com.plazoleta.usuarios_service.application.dtos.response.UserResponseDto;

public interface IUserHandler {
    void saveUserInUsers(UserRequestDto userRequestDto);
    Boolean existUserById(Long id);
    void saveRestaurantAndEmployee(UserRequestDto userRequestDto);
    UserResponseDto getUserById(Long userId);
    UserResponseDto getUserByEmail(String email);

}
