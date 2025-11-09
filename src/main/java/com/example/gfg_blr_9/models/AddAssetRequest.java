package com.example.gfg_blr_9.models;

import com.example.gfg_blr_9.enums.LibraryAssetType;

public class AddAssetRequest {
    private String name;
    private String description;
    private String author;
    private LibraryAssetType type;
    private LibraryAsset asset;
    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LibraryAsset getAsset() {
        return asset;
    }

    public void setAsset(LibraryAsset asset) {
        this.asset = asset;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LibraryAssetType getType() {
        return type;
    }

    public void setType(LibraryAssetType type) {
        this.type = type;
    }
}
