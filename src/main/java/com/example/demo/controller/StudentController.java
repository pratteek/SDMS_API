package com.example.demo.controller;

import com.example.demo.kafka.Producer;
import com.example.demo.student.Student;
import com.example.demo.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @Autowired
    private final Producer producer;

    public StudentController(StudentService studentService, Producer producer) {
        this.studentService = studentService;
        this.producer = producer;
    }


    @GetMapping("/")
    public ResponseEntity<?> getStudents(){
        return studentService.getStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Long id){
        return studentService.getStudentById(id);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> getStudentByName(@PathVariable String name){
        return studentService.getStudentByName(name);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getStudentByEmail(@PathVariable String email){
        return ResponseEntity.ok(studentService.getStudentByEmail(email));
    }


    @PostMapping("/")
    public ResponseEntity<?> addStudent(@Valid @RequestBody Student student){
        this.producer.sendMessage(student.getEmail());
        this.producer.sendMessage(student.getName());
       return studentService.addStudent(student);
    }


    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
    }

    @DeleteMapping("/name/{name}")
    public void deleteStudentByName(@PathVariable String name){
        studentService.deleteStudentByName(name);
    }

}
