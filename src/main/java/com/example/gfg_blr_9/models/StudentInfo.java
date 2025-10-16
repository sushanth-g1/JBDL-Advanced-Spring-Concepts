package com.example.gfg_blr_9.models;

import lombok.Data;

@Data
public class StudentInfo {
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String firstName;
    public String lastName;
}
