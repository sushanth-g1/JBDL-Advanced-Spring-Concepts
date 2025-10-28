package com.example.gfg_blr_9.services;

import com.example.gfg_blr_9.enums.LibraryUserRoles;
import com.example.gfg_blr_9.models.LibraryUser;
import com.example.gfg_blr_9.repositories.LibraryUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@Service
public class LibraryUserService {
    private LibraryUserRepository libraryUserRepository;

    @Autowired
    public LibraryUserService(LibraryUserRepository libraryUserRepository){
        this.libraryUserRepository = libraryUserRepository;
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
}
