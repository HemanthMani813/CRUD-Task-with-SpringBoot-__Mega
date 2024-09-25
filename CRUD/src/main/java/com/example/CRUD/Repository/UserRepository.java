package com.example.CRUD.Repository;


import com.example.CRUD.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByMobNum(String mobNum);
    boolean existsByPanNum(String panNum);
}

