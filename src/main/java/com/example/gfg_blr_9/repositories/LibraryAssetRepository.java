package com.example.gfg_blr_9.repositories;

import com.example.gfg_blr_9.models.LibraryAsset;
import com.example.gfg_blr_9.models.LoginRequest;
import org.springframework.data.repository.CrudRepository;

public interface LibraryAssetRepository extends CrudRepository<LibraryAsset, Long> {
}
