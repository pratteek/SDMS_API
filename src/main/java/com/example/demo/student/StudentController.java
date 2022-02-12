package com.example.demo.student;

import com.example.demo.repository.StudentRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
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
