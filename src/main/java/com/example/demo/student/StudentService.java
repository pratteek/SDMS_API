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
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public ResponseEntity<?> getStudents(){
        return ResponseEntity.ok(this.studentRepository.findAll());
    }

    public Optional<?> getStudentById(Long id){
        return this.studentRepository.findById(id);

    }

    public Student getStudentByName(String name){
        return this.studentRepository.findByName(name);
        //if(result.isEmpty())
            //throw new StudentNotFoundException("id: "+name);
    }

    public Student getStudentByEmail(String email){
      return this.studentRepository.findByEmail(email);
        //if(result.isEmpty())
            //throw new StudentNotFoundException("id: "+email);
    }

    public Student addStudent(Student student){
        return this.studentRepository.save(student);
    }

    public String deleteStudent(Long id){
        this.studentRepository.deleteById(id);
        return "Removed";
    }
    public String deleteStudentByName(String name){
        this.studentRepository.deleteByName(name);
        return "Removed";
    }

}
