package com.plazoleta.usuarios_service.infrastructure.output.token;


import com.plazoleta.usuarios_service.infrastructure.Security.TokenUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenAdapterTest {

    private TokenAdapter tokenAdapter;

    @BeforeEach
    void setUp() {
        tokenAdapter = new TokenAdapter();
    }

    @Test
    void getBearerToken_shouldReturnTokenFromHeader() {
        // Arrange
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer mockToken");

        ServletRequestAttributes attributes = mock(ServletRequestAttributes.class);
        when(attributes.getRequest()).thenReturn(mockRequest);

        try (MockedStatic<RequestContextHolder> mockedRequestContextHolder = mockStatic(RequestContextHolder.class)) {
            mockedRequestContextHolder.when(RequestContextHolder::getRequestAttributes).thenReturn(attributes);

            // Act
            String result = tokenAdapter.getBearerToken();

            // Assert
            assertNotNull(result);
            assertEquals("Bearer mockToken", result);
        }
    }

    @Test
    void getBearerToken_shouldThrowException_whenNoRequestAttributes() {
        // Arrange
        try (MockedStatic<RequestContextHolder> mockedRequestContextHolder = mockStatic(RequestContextHolder.class)) {
            mockedRequestContextHolder.when(RequestContextHolder::getRequestAttributes).thenReturn(null);

            // Act & Assert
            assertThrows(NullPointerException.class, () -> tokenAdapter.getBearerToken());
        }
    }

    @Test
    void getEmail_shouldReturnEmailFromToken() {
        // Arrange
        String mockToken = "Bearer mockToken";
        try (MockedStatic<TokenUtils> mockedTokenUtils = mockStatic(TokenUtils.class)) {
            mockedTokenUtils.when(() -> TokenUtils.getEmail("mockToken")).thenReturn("test@example.com");

            // Act
            String result = tokenAdapter.getEmail(mockToken);

            // Assert
            assertNotNull(result);
            assertEquals("test@example.com", result);
        }
    }

    @Test
    void getEmail_shouldThrowException_whenTokenIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> tokenAdapter.getEmail(null));
    }

    @Test
    void getUserAuthId_shouldReturnUserIdFromToken() {
        // Arrange
        String mockToken = "Bearer mockToken";
        try (MockedStatic<TokenUtils> mockedTokenUtils = mockStatic(TokenUtils.class)) {
            mockedTokenUtils.when(() -> TokenUtils.getUsuarioAutenticadoId("mockToken")).thenReturn(1L);

            // Act
            Long result = tokenAdapter.getUserAuthId(mockToken);

            // Assert
            assertNotNull(result);
            assertEquals(1L, result);
        }
    }

    @Test
    void getUserAuthId_shouldThrowException_whenTokenIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> tokenAdapter.getUserAuthId(null));
    }

    @Test
    void getUserAuthRol_shouldReturnUserRoleFromToken() {
        // Arrange
        String mockToken = "Bearer mockToken";
        try (MockedStatic<TokenUtils> mockedTokenUtils = mockStatic(TokenUtils.class)) {
            mockedTokenUtils.when(() -> TokenUtils.getUsuarioAutenticadoRol("mockToken")).thenReturn("ROLE_USER");

            // Act
            String result = tokenAdapter.getUserAuthRol(mockToken);

            // Assert
            assertNotNull(result);
            assertEquals("ROLE_USER", result);
        }
    }

    @Test
    void getUserAuthRol_shouldThrowException_whenTokenIsNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> tokenAdapter.getUserAuthRol(null));
    }
}
