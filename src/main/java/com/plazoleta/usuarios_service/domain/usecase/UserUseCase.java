package com.plazoleta.usuarios_service.domain.usecase;

import com.plazoleta.usuarios_service.domain.models.Rol;
import com.plazoleta.usuarios_service.domain.spi.passwordencoder.IPasswordEncoderPort;
import com.plazoleta.usuarios_service.domain.spi.persistence.IUserPersistencePort;
import com.plazoleta.usuarios_service.domain.api.IUserServicePort;
import com.plazoleta.usuarios_service.domain.models.User;

import com.plazoleta.usuarios_service.domain.spi.token.IToken;
import lombok.RequiredArgsConstructor;


import java.time.LocalDate;
import java.time.Period;

@RequiredArgsConstructor
public class UserUseCase implements IUserServicePort {

    private final IPasswordEncoderPort passwordEncoderPort;
    private final IUserPersistencePort userPersistencePort;
    private final IToken token;


    @Override
    public void saveUser(User user) {
        validaRolAuthAndNot(user);
        if(userPersistencePort.existsByEmail(user.getEmail())){
            throw new IllegalArgumentException("El email existe");
        }
        if ( (user.getRol().getId() == 3) && isMinor(user.getBirtDate())) {
            throw new IllegalArgumentException("El usuario es menor de edad.");
        }
        user.setPassword(passwordEncoderPort.encode(user.getPassword()));
        userPersistencePort.saveUser(user);
    }

    private boolean isMinor(LocalDate birthDate) {
        if(birthDate == null) return false;
        LocalDate today = LocalDate.now();
        return Period.between(birthDate, today).getYears() < 18;
    }

    private void validaRolAuthAndNot(User user){
        String bearerToken = token.getBearerToken();
        Rol rol = new Rol();
        String rolS = "";
        if(!(bearerToken == null)){
            rolS = token.getUserAuthRol(bearerToken);
        }
        if(rolS.equals("PROPIETARIO")){
            rol.setId(2L);
        }else
            if(rolS.equals("ADMIN")){
                rol.setId(3L);
            }else
                if(user.getRol().getId() == null){
                    rol.setId(1L);
                }else {
                    if (user.getRol().getId() == 1){
                        rol.setId(4L);
                    }
                }
            if(!(rol.getId() == null)){
                user.setRol(rol);
            }
    }
}

