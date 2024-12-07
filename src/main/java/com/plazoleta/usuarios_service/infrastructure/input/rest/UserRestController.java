package com.plazoleta.usuarios_service.infrastructure.input.rest;

import com.plazoleta.usuarios_service.application.dtos.request.UserRequestDto;
import com.plazoleta.usuarios_service.application.handler.IUserHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final IUserHandler userHandler;


    public UserRestController(IUserHandler userHandler){
        this.userHandler = userHandler;
    }
    @PostMapping("/create")
    public ResponseEntity<Void> saveUserInUsers(@Valid @RequestBody UserRequestDto userRequestDto){
        userHandler.saveUserInUsers(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
