package com.example.demo.repository;

import com.example.demo.student.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface StudentRepository extends MongoRepository<Student,Long> {

    @Query("{'name':?0}")
    Student findByName(String name);

    @Query("{'email':?0")
    Student findByEmail(String email);

    @Query(value = "'name':$0",delete = true)
    void deleteByName(String name);
}
