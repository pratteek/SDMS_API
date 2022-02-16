package com.example.demo.student;

import com.example.demo.exception.CustomResponseEntityExceptionHandler;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

//Service Layer
//Business Logic
@Service
public class StudentService extends CustomResponseEntityExceptionHandler {

    @Autowired
    private StudentRepository studentRepository;

    public ResponseEntity<?> getStudents(){
        return ResponseEntity.ok(this.studentRepository.findAll());
    }

    public ResponseEntity<?> getStudentById(Long id){
        Optional<?> result = this.studentRepository.findById(id);
        if(result.isEmpty())
            throw new StudentNotFoundException("id: "+id);

        return ResponseEntity.ok(result);

    }

    public ResponseEntity<?> getStudentByName(String name){
        Optional<?> result = Optional.ofNullable(this.studentRepository.findByName(name));
        if(result.isEmpty())
            throw new StudentNotFoundException("id: "+name);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<?> getStudentByEmail(String email){
        Optional<?> result = Optional.ofNullable(this.studentRepository.findByEmail(email));
        if(result.isEmpty())
            throw new StudentNotFoundException("id: "+email);

        return ResponseEntity.ok(result);
    }

    public ResponseEntity<?> addStudent(Student student){
        Student save = this.studentRepository.save(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}").buildAndExpand(save.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    public void deleteStudent(Long id){
        this.studentRepository.deleteById(id);
    }
    public void deleteStudentByName(String name){
        this.studentRepository.deleteByName(name);
    }

}
