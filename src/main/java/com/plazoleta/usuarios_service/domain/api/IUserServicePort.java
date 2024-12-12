package com.plazoleta.usuarios_service.domain.api;

import com.plazoleta.usuarios_service.domain.models.User;

public interface IUserServicePort {
    //methods for our service
    void saveUser(User user);

}
