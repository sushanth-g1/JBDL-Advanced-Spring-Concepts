package com.example.gfg_blr_9.models;

import lombok.Data;
import lombok.NonNull;

@Data
public class Records {
    @NonNull
    public String name;
    @NonNull
    public String email;
    @NonNull
    public String phone;
}
