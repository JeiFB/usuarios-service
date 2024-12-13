package com.plazoleta.usuarios_service.infrastructure.input.rest;

import com.plazoleta.usuarios_service.application.dtos.request.UserRequestDto;
import com.plazoleta.usuarios_service.application.dtos.response.UserResponseDto;
import com.plazoleta.usuarios_service.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserRestController {
    private final IUserHandler userHandler;

    public UserRestController(IUserHandler userHandler){
        this.userHandler = userHandler;
    }

    @Operation(summary = "Add a new owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content)
    })
    @PostMapping("/owner")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> saveOwner(@Valid @RequestBody UserRequestDto userRequestDto){
        userHandler.saveUserInUsers(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add a new employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Employee created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Employee already exists", content = @Content)
    })
    @PostMapping("/employee")
    @PreAuthorize("hasAuthority('PROPIETARIO')")
    public ResponseEntity<Void> saveEmployee(@Valid @RequestBody UserRequestDto employee) {
        userHandler.saveUserInUsers(employee);
        userHandler.saveRestaurantAndEmployee(employee);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "Add a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client created", content = @Content),
            @ApiResponse(responseCode = "409", description = "Client already exists", content = @Content)
    })
    @PostMapping("/client")
    public ResponseEntity<Void> saveClient(@Valid @RequestBody UserRequestDto userRequestDto){
        userHandler.saveUserInUsers(userRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Add a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created", content = @Content),
            @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> saveUser(@Valid @RequestBody UserRequestDto userRequestDto){
        userHandler.saveUserInUsers(userRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(summary = "Exist a user by ID (Boolean)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User returned", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("existsUserById/{id}")
    public  ResponseEntity<Boolean> existsUserById(@PathVariable(value = "id") Long userId){
        return ResponseEntity.ok(userHandler.existUserById(userId));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable(value = "email") String userEmail){
        return ResponseEntity.ok(userHandler.getUserByEmail(userEmail));
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable(value = "id")Long userId){
        return ResponseEntity.ok(userHandler.getUserById(userId));
    }
}
