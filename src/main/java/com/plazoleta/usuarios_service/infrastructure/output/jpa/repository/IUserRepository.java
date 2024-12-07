package com.plazoleta.usuarios_service.infrastructure.output.jpa.repository;

import com.plazoleta.usuarios_service.infrastructure.output.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity>  findOneByEmail(String email);
    boolean existsByEmail(String email);

}
