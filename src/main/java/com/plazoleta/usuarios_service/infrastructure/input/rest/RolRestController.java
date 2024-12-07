package com.plazoleta.usuarios_service.infrastructure.input.rest;


import com.plazoleta.usuarios_service.application.dtos.request.RolRequestDto;
import com.plazoleta.usuarios_service.application.dtos.response.RolResponseDto;
import com.plazoleta.usuarios_service.application.handler.IRolHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rol")
public class RolRestController {
    private final IRolHandler rolHandler;


    public RolRestController(IRolHandler rolHandler) {
        this.rolHandler = rolHandler;
    }


    @GetMapping("/{id}")
    public ResponseEntity<RolResponseDto> getRolById(@PathVariable(value = "id") Long id) {
        return  ResponseEntity.ok(rolHandler.getRolById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<RolResponseDto>> getAllRoles(){
        return ResponseEntity.ok(rolHandler.getAllRoles());
    }

    @PostMapping("/")
    public ResponseEntity<Void> saveRol(@RequestBody RolRequestDto rolRequestDto){
        rolHandler.saveRol(rolRequestDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRol(@PathVariable(value = "id")Long id){
        rolHandler.deleteRol(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
