package com.example.gfg_blr_9.models;

import com.example.gfg_blr_9.entity_listeners.UserEntityListeners;
import com.example.gfg_blr_9.enums.LibraryUserRoles;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "library_user")
//@EntityListeners(UserEntityListeners.class)
public class LibraryUser {

    public List<LibraryAsset> getContribution() {
        return contribution;
    }

    public void setContribution(List<LibraryAsset> contribution) {
        this.contribution = contribution;
    }

    @OneToMany(mappedBy = "contributor", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<LibraryAsset> contribution = new ArrayList<>();
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Transient
    private String nickname;

    public LibraryUserRoles getRole() {
        return role;
    }
    @Temporal(TemporalType.TIMESTAMP)
    private java.sql.Timestamp createdAt;

    public void setRole(LibraryUserRoles role) {
        this.role = role;
    }
    @Enumerated(EnumType.STRING)
    private LibraryUserRoles role;
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public java.sql.Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.sql.Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String password;


}
