package com.plazoleta.usuarios_service.application.handler;

import com.plazoleta.usuarios_service.application.dtos.request.UserRequestDto;

public interface IUserHandler {
    void saveUserInUsers(UserRequestDto userRequestDto);
}
