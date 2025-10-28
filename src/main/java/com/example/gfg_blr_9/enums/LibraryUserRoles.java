package com.example.gfg_blr_9.enums;

public enum LibraryUserRoles {
//    CUSTOMER,
//    LIBRARIAN,
//    ADMIN;

    CUSTOMER("Customer"),
    LIBRARIAN("Librarian"),
    ADMIN("Admin");

    private String value;
    LibraryUserRoles(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }

}
