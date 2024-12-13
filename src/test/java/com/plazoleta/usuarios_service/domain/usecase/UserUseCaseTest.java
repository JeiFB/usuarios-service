package com.plazoleta.usuarios_service.domain.usecase;

import com.plazoleta.usuarios_service.domain.models.Restaurant;
import com.plazoleta.usuarios_service.domain.models.RestaurantAndEmployee;
import com.plazoleta.usuarios_service.domain.models.Rol;
import com.plazoleta.usuarios_service.domain.models.User;
import com.plazoleta.usuarios_service.domain.spi.feignclients.IRestaurantAndEmployeeFeignClientPort;
import com.plazoleta.usuarios_service.domain.spi.feignclients.IRestaurantFeingClientPort;
import com.plazoleta.usuarios_service.domain.spi.passwordencoder.IPasswordEncoderPort;
import com.plazoleta.usuarios_service.domain.spi.persistence.IUserPersistencePort;
import com.plazoleta.usuarios_service.domain.spi.token.IToken;
import com.plazoleta.usuarios_service.domain.usecase.UserUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserUseCaseTest {

    @Mock
    private IPasswordEncoderPort passwordEncoderPort;

    @Mock
    private IUserPersistencePort userPersistencePort;

    @Mock
    private IRestaurantFeingClientPort restaurantFeingClientPort;

    @Mock
    private IRestaurantAndEmployeeFeignClientPort restaurantAndEmployeeFeignClientPort;

    @Mock
    private IToken token;

    @InjectMocks
    private UserUseCase userUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser_Success() {
        User user = new User();
        user.setName("John Doe");
        user.setBirtDate(LocalDate.of(2000, 1, 1));
        user.setPassword("password");
        Rol rol = new Rol();
        rol.setId(1L);
        user.setRol(rol);

        when(passwordEncoderPort.encode("password")).thenReturn("encodedPassword");

        userUseCase.saveUser(user);

        assertEquals("encodedPassword", user.getPassword());
        verify(userPersistencePort, times(1)).saveUser(user);
    }

    @Test
    void testSaveUser_ThrowsIllegalArgumentExceptionForMinor() {
        User user = new User();
        user.setName("Jane Doe");
        user.setBirtDate(LocalDate.now().minusYears(17));
        Rol rol = new Rol();
        rol.setId(3L);
        user.setRol(rol);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> userUseCase.saveUser(user));

        assertEquals("El usuario es menor de edad.", exception.getMessage());
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }

    @Test
    void testExistsUserById_ReturnsTrue() {
        when(userPersistencePort.existsUserById(1L)).thenReturn(true);

        boolean exists = userUseCase.existsUserById(1L);

        assertTrue(exists);
        verify(userPersistencePort, times(1)).existsUserById(1L);
    }

    @Test
    void testSaveRestaurantAndEmployee_Success() {
        User user = new User();
        user.setEmail("test@example.com");

        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        User persistedUser = new User();
        persistedUser.setId(2L);

        when(token.getBearerToken()).thenReturn("Bearer token");
        when(token.getUserAuthId("Bearer token")).thenReturn(1L);
        when(restaurantFeingClientPort.getRestaurantByOwnerId(1L)).thenReturn(restaurant);
        when(userPersistencePort.getUserByEmail("test@example.com")).thenReturn(persistedUser);

        userUseCase.saveRestaurantAndEmployee(user);

        verify(restaurantAndEmployeeFeignClientPort, times(1)).saveEmployeeInRestaurant(any(RestaurantAndEmployee.class));
    }

    @Test
    void testGetUserById_ReturnsUser() {
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");

        when(userPersistencePort.getUserById(1L)).thenReturn(user);

        User result = userUseCase.getUserById(1L);

        assertEquals(user, result);
        verify(userPersistencePort, times(1)).getUserById(1L);
    }

    @Test
    void testGetUserByEmail_ReturnsUser() {
        User user = new User();
        user.setEmail("test@example.com");

        when(userPersistencePort.getUserByEmail("test@example.com")).thenReturn(user);

        User result = userUseCase.getUserByEmail("test@example.com");

        assertEquals(user, result);
        verify(userPersistencePort, times(1)).getUserByEmail("test@example.com");
    }
}
