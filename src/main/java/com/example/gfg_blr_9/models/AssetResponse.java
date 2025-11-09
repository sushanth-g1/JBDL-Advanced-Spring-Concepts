package com.example.gfg_blr_9.models;

import com.example.gfg_blr_9.enums.LibraryAssetType;

import java.sql.Timestamp;

public class AssetResponse {

    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
    }

    public LibraryAssetType getType() {
        return type;
    }

    public void setType(LibraryAssetType type) {
        this.type = type;
    }

    private String description;
    private String author;
    private LibraryAssetType type;
    private Timestamp publishedAt;
}
