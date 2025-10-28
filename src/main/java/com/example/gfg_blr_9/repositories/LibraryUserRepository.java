package com.example.gfg_blr_9.repositories;

import com.example.gfg_blr_9.models.LibraryUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryUserRepository extends CrudRepository<LibraryUser, Long> {

    @Query(value = "select * from library_user where username=?1 and password=?2", nativeQuery = true)
    public LibraryUser findByUserNamePassword(String username, String password);

    @Query(value = "select * from library_user where username=?1", nativeQuery = true)
    public LibraryUser findByUsername(String username);
}
