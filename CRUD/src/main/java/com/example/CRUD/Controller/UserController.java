package com.example.CRUD.Controller;


import com.example.CRUD.Model.User;
import com.example.CRUD.Repository.UserRepository;
import com.example.CRUD.Repository.ManagerRepository;
import com.example.CRUD.Request.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @PostMapping("/create_user")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request) {
        // Validate full name
        if (request.getFullName() == null || request.getFullName().isEmpty()) {
            return ResponseEntity.badRequest().body("Full name must not be empty");
        }

        // Validate and format mobile number
        String mobNum = request.getMobNum().replaceAll("[^\\d]", "");  // Remove non-digits
        if (mobNum.length() != 10) {
            return ResponseEntity.badRequest().body("Invalid mobile number. Must be 10 digits.");
        }

        // Validate and format PAN number
        String panNum = request.getPanNum().toUpperCase();
        if (!panNum.matches("[A-Z]{5}[0-9]{4}[A-Z]{1}")) {
            return ResponseEntity.badRequest().body("Invalid PAN number format.");
        }

        // Validate manager_id if present
        if (request.getManagerId() != null && !managerRepository.existsByManagerIdAndIsActive(request.getManagerId(), true)) {
            return ResponseEntity.badRequest().body("Invalid or inactive manager ID.");
        }

        // Check if PAN already exists
        if (userRepository.existsByPanNum(panNum)) {
            return ResponseEntity.badRequest().body("PAN number already exists.");
        }

        // Create and save the new user
        User user = new User();
        user.setUserId(UUID.randomUUID());
        user.setFullName(request.getFullName());
        user.setMobNum(mobNum);
        user.setPanNum(panNum);
        user.setManagerId(request.getManagerId());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setIsActive(true);

        userRepository.save(user);

        return ResponseEntity.ok("User created successfully!");
    }

    @PostMapping("/get_users")
    public ResponseEntity<?> getUsers(@RequestBody Map<String, String> filters) {
        // If mob_num or user_id or manager_id is provided, filter based on them
        Optional<User> users;
        
        if (filters.containsKey("mob_num")) {
            users = userRepository.findByMobNum(filters.get("mob_num"));
        } else if (filters.containsKey("user_id")) {
            users = userRepository.findById(UUID.fromString(filters.get("user_id")));
        } else if (filters.containsKey("manager_id")) {
            users = userRepository.findById(UUID.fromString(filters.get("manager_id")));
        } else {
            users = Optional.empty();
        }

        if (users.isEmpty()) {
            return ResponseEntity.ok("No users found.");
        }

        return ResponseEntity.ok(users);
    }

}

