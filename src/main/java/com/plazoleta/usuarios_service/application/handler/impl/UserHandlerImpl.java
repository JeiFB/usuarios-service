package com.plazoleta.usuarios_service.application.handler.impl;

import com.plazoleta.usuarios_service.application.dtos.request.UserRequestDto;
import com.plazoleta.usuarios_service.application.dtos.response.UserResponseDto;
import com.plazoleta.usuarios_service.application.handler.IUserHandler;
import com.plazoleta.usuarios_service.application.mapper.IUserRequestMapper;
import com.plazoleta.usuarios_service.application.mapper.IUserResponseMapper;
import com.plazoleta.usuarios_service.domain.api.IUserServicePort;
import com.plazoleta.usuarios_service.domain.models.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public void saveUserInUsers(UserRequestDto userRequestDto) {
        User user = userRequestMapper.toUser(userRequestDto);
        userServicePort.saveUser(user);
    }

    @Override
    public Boolean existUserById(Long id) {
        return userServicePort.existsUserById(id);
    }

    @Override
    public void saveRestaurantAndEmployee(UserRequestDto userRequestDto) {
        userServicePort.saveRestaurantAndEmployee(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public UserResponseDto getUserById(Long userId) {
        return userResponseMapper.toResponse(userServicePort.getUserById(userId));
    }

    @Override
    public UserResponseDto getUserByEmail(String email) {
        return userResponseMapper.toResponse(userServicePort.getUserByEmail(email));
    }
}
