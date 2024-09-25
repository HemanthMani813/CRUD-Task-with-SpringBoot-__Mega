package com.example.CRUD.Model;



import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID userId;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String mobNum;

    @Column(nullable = false, unique = true)
    private String panNum;

    private UUID managerId;

    @Column(nullable = false)
    private boolean isActive = true;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    public User() {
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    // Constructor with parameters
    public User(String fullName, String mobNum, String panNum, UUID managerId) {
        this.userId = UUID.randomUUID();
        this.fullName = fullName;
        this.mobNum = mobNum;
        this.panNum = panNum;
        this.managerId = managerId;
        this.isActive = true;
        this.createdAt = new Timestamp(System.currentTimeMillis());
    }

    // Getters and Setters
    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMobNum() {
        return mobNum;
    }

    public void setMobNum(String mobNum) {
        this.mobNum = mobNum;
    }

    public String getPanNum() {
        return panNum;
    }

    public void setPanNum(String panNum) {
        this.panNum = panNum;
    }

    public UUID getManagerId() {
        return managerId;
    }

    public void setManagerId(UUID managerId) {
        this.managerId = managerId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}
