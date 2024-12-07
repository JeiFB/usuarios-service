package com.plazoleta.usuarios_service.Security;

import com.plazoleta.usuarios_service.domain.models.User;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.entity.UserEntity;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findOneByEmail(email).orElseThrow(()->new UsernameNotFoundException("El usuario con email "+email+" no existe."));
        User user= userEntityMapper.toUser(userEntity);

        return new UserDetailsImpl(user);
    }
}
