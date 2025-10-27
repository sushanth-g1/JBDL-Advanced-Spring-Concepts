package com.example.gfg_blr_9.repositories;

import com.example.gfg_blr_9.models.StudentContact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentContactRepository extends CrudRepository<StudentContact, Long> {

    @Query(value = "select * from student_contacts where id = ?1", nativeQuery = true)
    public StudentContact findByContactId(Long contactId);
}
