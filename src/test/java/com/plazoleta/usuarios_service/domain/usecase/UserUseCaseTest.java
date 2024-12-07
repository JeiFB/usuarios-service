package com.plazoleta.usuarios_service.domain.usecase;

import com.plazoleta.usuarios_service.domain.models.Rol;
import com.plazoleta.usuarios_service.domain.models.User;
import com.plazoleta.usuarios_service.domain.spi.passwordencoder.IPasswordEncoderPort;
import com.plazoleta.usuarios_service.domain.spi.persistence.IUserPersistencePort;
import com.plazoleta.usuarios_service.domain.spi.token.IToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IPasswordEncoderPort passwordEncoderPort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IToken token;

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
        user.setRol(new Rol(2L, null, null));
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
        user.setRol(new Rol(3L, null, null));
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
        user.setRol(new Rol(2L, null, null));
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

    @Test
    void saveUser_shouldSetRoleToAdmin_whenBearerTokenIsAdmin() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setRol(new Rol(null, null, null)); // Sin rol definido inicialmente
        user.setPassword("password123");

        when(token.getBearerToken()).thenReturn("mockedBearerToken");
        when(token.getUserAuthRol("mockedBearerToken")).thenReturn("ADMIN");
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);

        // Act
        userUseCase.saveUser(user);

        // Assert
        assertEquals(3L, user.getRol().getId());
        verify(token, times(1)).getUserAuthRol("mockedBearerToken");
        verify(userPersistencePort, times(1)).saveUser(user);
    }

    @Test
    void saveUser_shouldSetRoleToOwner_whenBearerTokenIsOwner() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setRol(new Rol(null, null, null)); // Sin rol definido inicialmente
        user.setPassword("password123");

        when(token.getBearerToken()).thenReturn("mockedBearerToken");
        when(token.getUserAuthRol("mockedBearerToken")).thenReturn("PROPIETARIO");
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);

        // Act
        userUseCase.saveUser(user);

        // Assert
        assertEquals(2L, user.getRol().getId());
        verify(token, times(1)).getUserAuthRol("mockedBearerToken");
        verify(userPersistencePort, times(1)).saveUser(user);
    }

    @Test
    void saveUser_shouldSetRoleToDefault_whenNoRoleIsProvided() {
        // Arrange
        User user = new User();
        user.setEmail("test@example.com");
        user.setRol(new Rol(null, null, null)); // Sin rol definido inicialmente
        user.setPassword("password123");

        when(token.getBearerToken()).thenReturn(null); // No hay token
        when(userPersistencePort.existsByEmail(user.getEmail())).thenReturn(false);

        // Act
        userUseCase.saveUser(user);

        // Assert
        assertEquals(1L, user.getRol().getId()); // Rol por defecto
        verify(userPersistencePort, times(1)).saveUser(user);
    }
}
