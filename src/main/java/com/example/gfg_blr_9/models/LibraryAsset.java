package com.example.gfg_blr_9.models;

import com.example.gfg_blr_9.enums.LibraryAssetType;
import com.example.gfg_blr_9.enums.LibraryUserRoles;
import jakarta.persistence.*;
import lombok.Generated;

import java.sql.Timestamp;

@Entity
@Table(name = "library_assets")
public class LibraryAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String author;

    @Enumerated(EnumType.STRING)
    private LibraryAssetType type;

    public Timestamp getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Timestamp publishedAt) {
        this.publishedAt = publishedAt;
    }

    public LibraryUser getContributor() {
        return contributor;
    }

    public void setContributor(LibraryUser contributor) {
        this.contributor = contributor;
    }

    @Column(name = "published_at")
    private Timestamp publishedAt;

//    @ManyToOne(cascade = CascadeType.ALL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contributor_id", referencedColumnName = "id")
    private LibraryUser contributor;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LibraryAssetType getType() {
        return type;
    }

    public void setType(LibraryAssetType type) {
        this.type = type;
    }



}
