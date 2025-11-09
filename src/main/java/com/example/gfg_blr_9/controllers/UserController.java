package com.example.gfg_blr_9.controllers;

import com.example.gfg_blr_9.models.*;
import com.example.gfg_blr_9.services.LibraryUserService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final LibraryUserService libraryUserService;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    public UserController(LibraryUserService libraryUserService){
        this.libraryUserService = libraryUserService;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

//    @PostMapping("/v1/digitalLibrary/login")
//    public String login(@RequestBody LoginRequest loginRequest){
//      if(this.libraryUserService.login(loginRequest.getUsername(),loginRequest.getPassword())){
//          return "Logged in succesfully";
//      }
//      return "Invalid username and password";
//    }

    @PostMapping("/v1/digitalLibrary/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest){
        if(this.libraryUserService.login(loginRequest.getUsername(),loginRequest.getPassword())){
            return ResponseEntity.ok("Logged in succesfully");
        }
        return ResponseEntity.badRequest().body("Invalid username and password");
    }
    @PostMapping("/v1/digitalLibrary/register")
    public ResponseEntity<String> register(@RequestBody RegistrationRequest registrationRequest){
        LibraryUser libraryUser = libraryUserService.register(registrationRequest.getUsername(),registrationRequest.getPassword(),registrationRequest.getRole());
        libraryUser.getRole().getValue();
        if(libraryUser == null){
            return ResponseEntity.badRequest().body("Username already exist");
        }
        return ResponseEntity.ok("User Registered Succesfully");

    }
    @PostMapping("/v1/addasset")
    public ResponseEntity<AssetResponse> addAsset(@RequestBody AddAssetRequest addAssetRequest){
        LibraryAsset libraryAsset = libraryUserService.addAsset(addAssetRequest);
        if(libraryAsset == null){
            return ResponseEntity.badRequest().body(new AssetResponse());
        } else {
           // AssetResponse assetResponse = objectMapper.convertValue(libraryAsset1, AssetResponse.class);
            AssetResponse assetResponse = new AssetResponse();
            assetResponse.setAuthor(libraryAsset.getAuthor());
            assetResponse.setDescription(libraryAsset.getDescription());
            assetResponse.setPublishedAt(libraryAsset.getPublishedAt());
            return ResponseEntity.ok(assetResponse);
        }
    }
    @GetMapping("/v1/getasset")
    public ResponseEntity<List<AssetResponse>> getAsset(@RequestParam("username") String username){
        return ResponseEntity.ok().body(libraryUserService.findAssets(username));
    }

}
