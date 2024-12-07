package com.plazoleta.usuarios_service.domain.usecase;

import com.plazoleta.usuarios_service.domain.spi.passwordencoder.IPasswordEncoderPort;
import com.plazoleta.usuarios_service.domain.spi.persistence.IUserPersistencePort;
import com.plazoleta.usuarios_service.domain.api.IUserServicePort;
import com.plazoleta.usuarios_service.domain.models.User;

import lombok.RequiredArgsConstructor;


import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IPasswordEncoderPort passwordEncoderPort;
    private final IUserPersistencePort userPersistencePort;

    @Override
    public void saveUser(User user) {
        if(userPersistencePort.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("El email existe");
        }
        if ( (user.getRol().getId() == 1) && isMinor(user.getBirtDate())) {
            throw new IllegalArgumentException("El usuario es menor de edad.");
        }
        user.setPassword(passwordEncoderPort.encode(user.getPassword()));
        userPersistencePort.saveUser(user);
    }

    private boolean isMinor(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears() < 18;
    }
}
