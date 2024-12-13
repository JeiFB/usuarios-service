package com.plazoleta.usuarios_service.infrastructure.output.jpa.adapter;

import com.plazoleta.usuarios_service.domain.models.User;
import com.plazoleta.usuarios_service.domain.spi.persistence.IUserPersistencePort;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.entity.UserEntity;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(email);
        return userEntityMapper.toUser(userEntityOptional.orElse(null));
    }

    @Override
    public Boolean existsUserById(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User getUserById(Long userId) {
        Optional<UserEntity> userEntityOptional = userRepository.findById(userId);
        UserEntity userEntity = userEntityOptional.orElse(null);
        return userEntityMapper.toUser(userEntity);
    }
}
