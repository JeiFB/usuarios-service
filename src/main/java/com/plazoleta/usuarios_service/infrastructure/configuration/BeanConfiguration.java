package com.plazoleta.usuarios_service.infrastructure.configuration;

import com.plazoleta.usuarios_service.domain.api.IRolServicePort;
import com.plazoleta.usuarios_service.domain.api.IUserServicePort;
import com.plazoleta.usuarios_service.domain.spi.passwordencoder.IPasswordEncoderPort;
import com.plazoleta.usuarios_service.domain.spi.persistence.IRolPersistencePort;
import com.plazoleta.usuarios_service.domain.spi.persistence.IUserPersistencePort;
import com.plazoleta.usuarios_service.domain.usecase.RolUseCase;
import com.plazoleta.usuarios_service.domain.usecase.UserUseCase;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.adapter.RolJpaAdapter;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.adapter.UserJpaAdapter;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.mapper.IRolEntityMapper;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.repository.IRolRepository;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.repository.IUserRepository;
import com.plazoleta.usuarios_service.infrastructure.output.passwordencoder.BCryptPasswordEncoderAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRolRepository rolRepository;
    private final IRolEntityMapper rolEntityMapper;


    @Bean
    public  IPasswordEncoderPort passwordEncoderPort(){
        return new BCryptPasswordEncoderAdapter();
    }
    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }
    @Bean
    public IUserServicePort userServicePort(){
        return  new UserUseCase(passwordEncoderPort(),userPersistencePort());
    }

    @Bean
    public IRolPersistencePort rolPersistencePort(){
        return new RolJpaAdapter(rolRepository, rolEntityMapper);
    }

    @Bean
    public IRolServicePort rolServicePort(){
        return  new RolUseCase(rolPersistencePort());
    }
}
