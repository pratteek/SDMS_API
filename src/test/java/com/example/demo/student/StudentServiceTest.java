package com.example.demo.student;

import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Autowired
    private  StudentService studentService;

    @BeforeEach
    void setUp(){
        this.studentService = new StudentService(this.studentRepository);
    }


    @Test
    void getStudents() {
        studentService.getStudents();
        verify(studentRepository).findAll();
    }

    @Test
    void getStudentById() {
        final Student student = new Student();
       Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
       assertEquals(Optional.of(student),studentService.getStudentById(1L));
    }

    @Test
    void getStudentByName() {
        final Student student = new Student();
        Mockito.when(studentRepository.findByName("Nidhi")).thenReturn(student);
        assertEquals(student,studentService.getStudentByName("Nidhi"));
    }

    @Test
    void getStudentByEmail() {
        final Student student = new Student();
        Mockito.when(studentRepository.findByEmail("prateek@gmail.com")).thenReturn(student);
        assertEquals(student,studentService.getStudentByEmail("prateek@gmail.com"));
    }

    @Test
    void addStudent() {
        final Student student = new Student();
        student.setId(100L);
        student.setName("Raf");
        student.setAge(29);
        student.setDob(LocalDate.of(1992,8,5));
        student.setEmail("raf@tennis.com");

        Mockito.when(studentRepository.save(student)).thenReturn(student);
        assertEquals(studentService.addStudent(student),student);
    }


    @Test
    void deleteStudent() {
        String res = studentService.deleteStudent(1L);
        assertEquals("Removed",res);
    }

    @Test
    void deleteStudentByName() {
        String res = studentService.deleteStudentByName("Prateek");
        assertEquals("Removed",res);
    }
}