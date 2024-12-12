package com.plazoleta.usuarios_service.domain.spi.persistence;

import com.plazoleta.usuarios_service.domain.models.User;


public interface IUserPersistencePort {
    void saveUser(User user);
    User getUserByEmail(String email);
    Boolean existsByEmail(String email);
}
