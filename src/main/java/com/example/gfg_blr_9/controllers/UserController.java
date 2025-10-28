package com.example.gfg_blr_9.controllers;

import com.example.gfg_blr_9.models.LibraryUser;
import com.example.gfg_blr_9.models.LoginRequest;
import com.example.gfg_blr_9.models.RegistrationRequest;
import com.example.gfg_blr_9.services.LibraryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final LibraryUserService libraryUserService;

    @Autowired
    public UserController(LibraryUserService libraryUserService){
        this.libraryUserService = libraryUserService;
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

}
