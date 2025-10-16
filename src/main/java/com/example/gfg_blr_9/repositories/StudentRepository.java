package com.example.gfg_blr_9.repositories;

import com.example.gfg_blr_9.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    @Query(value = "select * from students where first_name = ?1", nativeQuery = true)
    public List<Student> findByFirstName(String name);

    @Query(value = "select * from students where lastName = :lastName", nativeQuery = true)
    public List<Student> findByLastName(@Param(value= "lastName") String name);

// TRY IN JPQL QUERY also   @Query(value = "SELECT S FROM STUDENTS WHERE FIRST_NAME", nativeQuery = true)
//    public List<Student> findByFistName(String name);
}
