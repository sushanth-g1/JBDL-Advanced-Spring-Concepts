package com.example.gfg_blr_9.models;


import com.example.gfg_blr_9.enums.LibraryUserRoles;
import lombok.NonNull;

public class RegistrationRequest {

    @NonNull
    private String username;
    @NonNull
    private String password;
    @NonNull
    private LibraryUserRoles role;

    public LibraryUserRoles getRole() {
        return role;
    }

    public void setRole(LibraryUserRoles role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
