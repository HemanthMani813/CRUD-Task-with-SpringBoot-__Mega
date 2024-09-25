package com.example.CRUD.Repository;


import com.example.CRUD.Model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ManagerRepository extends JpaRepository<Manager, UUID> {
    boolean existsByManagerIdAndIsActive(UUID managerId, boolean isActive);
}

