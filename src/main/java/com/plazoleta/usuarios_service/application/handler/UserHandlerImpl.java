package com.plazoleta.usuarios_service.application.handler;

import com.plazoleta.usuarios_service.application.dtos.request.UserRequestDto;
import com.plazoleta.usuarios_service.application.mapper.IUserRequestMapper;
import com.plazoleta.usuarios_service.domain.api.IUserServicePort;
import com.plazoleta.usuarios_service.domain.models.User;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserHandlerImpl implements IUserHandler{

    private final IUserServicePort iUserServicePort;
    private final IUserRequestMapper iUserRequestMapper;

    public UserHandlerImpl(IUserServicePort iUserServicePort, IUserRequestMapper iUserRequestMapper){
        this.iUserServicePort = iUserServicePort;
        this.iUserRequestMapper = iUserRequestMapper;
    }
    @Override
    public void saveUserInUsers(UserRequestDto userRequestDto) {
        User user = iUserRequestMapper.toUser(userRequestDto);
        iUserServicePort.saveUser(user);
    }
}
