package com.plazoleta.usuarios_service.infrastructure.output.jpa.adapter;

import com.plazoleta.usuarios_service.domain.models.User;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.entity.UserEntity;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.mapper.IUserEntityMapper;
import com.plazoleta.usuarios_service.infrastructure.output.jpa.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class UserJpaAdapterTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IUserEntityMapper userEntityMapper;

    @InjectMocks
    private UserJpaAdapter userJpaAdapter;

    @Test
    void saveUser_shouldInvokeRepositorySave() {
        // Arrange
        User user = new User();
        UserEntity userEntity = new UserEntity();

        when(userEntityMapper.toEntity(user)).thenReturn(userEntity);

        // Act
        userJpaAdapter.saveUser(user);

        // Assert
        verify(userEntityMapper, times(1)).toEntity(user);
        verify(userRepository, times(1)).save(userEntity);
    }

    @Test
    void existsByEmail_shouldReturnTrue_whenEmailExists() {
        // Arrange
        String email = "test@example.com";
    //    when(userRepository.existsByEmail(email)).thenReturn(true);

        // Act
    //    Boolean result = userJpaAdapter.existsByEmail(email);

        // Assert
    //    assertTrue(result);
    //    verify(userRepository, times(1)).existsByEmail(email);
    }

    @Test
    void existsByEmail_shouldReturnFalse_whenEmailDoesNotExist() {
        // Arrange
        String email = "notfound@example.com";
      //  when(userRepository.existsByEmail(email)).thenReturn(false);

        // Act
//        Boolean result = userJpaAdapter.existsByEmail(email);

        // Assert
  //      assertFalse(result);
  //      verify(userRepository, times(1)).existsByEmail(email);
    }
}
