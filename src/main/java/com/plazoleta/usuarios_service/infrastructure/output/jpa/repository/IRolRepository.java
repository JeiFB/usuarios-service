package com.plazoleta.usuarios_service.infrastructure.output.jpa.repository;

import com.plazoleta.usuarios_service.infrastructure.output.jpa.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends JpaRepository<RolEntity, Long>{
}
