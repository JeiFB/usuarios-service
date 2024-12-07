package com.plazoleta.usuarios_service.domain.usecase;

import com.plazoleta.usuarios_service.domain.models.Rol;
import com.plazoleta.usuarios_service.domain.models.User;
import com.plazoleta.usuarios_service.domain.spi.passwordencoder.IPasswordEncoderPort;
import com.plazoleta.usuarios_service.domain.spi.persistence.IUserPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IPasswordEncoderPort passwordEncoderPort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveUser_shouldThrowException_whenEmailAlreadyExists() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setRol(new Rol(2L, null,null));
        user.setPassword("password123");

        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(true);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userUseCase.saveUser(user));
        verify(userPersistencePort, times(1)).existsByEmail(user.getEmail());
    }

    @Test
    void saveUser_shouldThrowException_whenUserIsMinor() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setRol(new Rol(1L, null,null));
        user.setPassword("password123");
        user.setBirtDate(LocalDate.now().minusYears(16)); // Menor de edad

        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> userUseCase.saveUser(user));
        verify(userPersistencePort, times(1)).existsByEmail(user.getEmail());
        verifyNoInteractions(passwordEncoderPort);
    }

    @Test
    void saveUser_shouldSaveUser_whenAllConditionsAreMet() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setRol(new Rol(2L, null,null));
        user.setPassword("password123");
        user.setBirtDate(LocalDate.now().minusYears(25)); // Mayor de edad

        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);
        when(passwordEncoderPort.encode("password123")).thenReturn("password123");

        // Act
        userUseCase.saveUser(user);

        // Assert
        verify(userPersistencePort, times(1)).existsByEmail(user.getEmail());
        verify(passwordEncoderPort, times(1)).encode(user.getPassword());
        verify(userPersistencePort, times(1)).saveUser(user);
    }
}
