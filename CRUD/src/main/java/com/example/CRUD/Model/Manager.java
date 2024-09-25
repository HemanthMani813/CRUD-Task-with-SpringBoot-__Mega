package com.example.CRUD.Model;


import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID managerId;

    @Column(nullable = false)
    private String managerName;

    @Column(nullable = false)
    private boolean isActive = true;

    public Manager() {}

    public Manager(String managerName) {
        this.managerName = managerName;
        this.isActive = true;
    }

    // Getters and Setters
    public UUID getManagerId() {
        return managerId;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
