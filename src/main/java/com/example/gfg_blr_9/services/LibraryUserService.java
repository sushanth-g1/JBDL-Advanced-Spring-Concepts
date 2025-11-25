package com.example.gfg_blr_9.services;

import com.example.gfg_blr_9.enums.LibraryUserRoles;
import com.example.gfg_blr_9.models.AddAssetRequest;
import com.example.gfg_blr_9.models.AssetResponse;
import com.example.gfg_blr_9.models.LibraryAsset;
import com.example.gfg_blr_9.models.LibraryUser;
import com.example.gfg_blr_9.pubsub.RedisMessagePublisher;
import com.example.gfg_blr_9.repositories.LibraryAssetRepository;
import com.example.gfg_blr_9.repositories.LibraryUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LibraryUserService {
    private static final Logger log = LoggerFactory.getLogger(LibraryUserService.class);
    private LibraryUserRepository libraryUserRepository;
    private LibraryAssetRepository libraryAssetRepository;
    private RedisCacheManager redisCacheManager;
    private RedisTemplate<String,String> stringRedisTemplate2;
    private RedisMessagePublisher publisher;

    @Autowired
    public LibraryUserService(LibraryUserRepository libraryUserRepository, LibraryAssetRepository libraryAssetRepository, RedisCacheManager redisCacheManager, RedisTemplate<String,String> stringRedisTemplate2, RedisMessagePublisher publisher){
        this.libraryUserRepository = libraryUserRepository;
        this.libraryAssetRepository = libraryAssetRepository;
        this.redisCacheManager = redisCacheManager;
        this.stringRedisTemplate2 = stringRedisTemplate2;
        this.publisher = publisher;
    }

    public String getPassword(String username){

//        Cache cache = redisCacheManager.getCache(username);
//        if(cache != null && cache.get("password") != null){
//            log.info("cache retained");
//            return String.valueOf(cache.get("password"));
//        }
        if(this.stringRedisTemplate2.hasKey(username)){
            log.info("cache retained using redis template");
            publisher.publish("cache HIT for username : "+username);
            return this.stringRedisTemplate2.opsForValue().get(username);
        }
        LibraryUser libraryUser = libraryUserRepository.findByUsername(username);
        stringRedisTemplate2.opsForValue().set(username, libraryUser.getPassword());
        //cache.put("password", libraryUser!= null ? libraryUser.getPassword() : "");
        publisher.publish("cache MISS for username : "+username);
        log.info("Cache written using redis template");
        return String.valueOf(libraryUser!= null ? libraryUser.getPassword() : "");
    }



    public LibraryUser register(String username, String password, LibraryUserRoles role){
        if (libraryUserRepository.findByUsername(username) != null ) {
            return null;
        }
        LibraryUser libraryUser = new LibraryUser();
        libraryUser.setUsername(username);
        libraryUser.setPassword(password);
        libraryUser.setRole(role);
        libraryUser.setNickname(UUID.randomUUID().toString());
        libraryUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        
        libraryUserRepository.save(libraryUser);
        return libraryUser;
    }
    public boolean login(String username, String password){
        LibraryUser libraryUser = libraryUserRepository.findByUserNamePassword(username,password);
        return libraryUser !=  null;
    }
    public LibraryAsset addAsset(AddAssetRequest addAssetRequest) {
        LibraryUser libraryUser = libraryUserRepository.findByUsername(addAssetRequest.getUsername());

        if (libraryUser != null && libraryUser.getRole().equals(LibraryUserRoles.ADMIN)) {
            LibraryAsset libraryAsset = new LibraryAsset();
            libraryAsset.setAuthor(addAssetRequest.getAuthor());
            libraryAsset.setType(addAssetRequest.getType());
            libraryAsset.setName(addAssetRequest.getName());
            libraryAsset.setDescription(addAssetRequest.getDescription());
            libraryAsset.setPublishedAt(new Timestamp(System.currentTimeMillis()));
            libraryAsset.setContributor(libraryUser);
            //libraryUser.getContribution().add(libraryAsset);
            return libraryAssetRepository.save(libraryAsset);

        }
        return null;

    }
    public List<AssetResponse> findAssets(String username){
        LibraryUser libraryUser = libraryUserRepository.findByUsername(username);
        if(libraryUser != null){
            List<LibraryAsset> libraryAssets = libraryUser.getContribution();
            List<AssetResponse> assetResponses = new ArrayList<>();
            for (LibraryAsset libraryAsset : libraryAssets){
                AssetResponse assetResponse = new AssetResponse();
                assetResponse.setAuthor(libraryAsset.getAuthor());
                assetResponse.setName(libraryAsset.getName());
                assetResponse.setDescription(libraryAsset.getDescription());
                assetResponse.setPublishedAt(libraryAsset.getPublishedAt());
                assetResponse.setType(libraryAsset.getType());
                assetResponses.add(assetResponse);
            }
            return assetResponses;
        }
        return null;

    }
}
