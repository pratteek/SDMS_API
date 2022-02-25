package com.example.demo

import com.example.demo.repository.StudentRepository
import com.example.demo.student.Student
import com.example.demo.student.StudentService
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest
class DemoSpecification extends Specification{

    def mockStudentRepository = Mock(StudentRepository.class);
    def studentService = new StudentService(mockStudentRepository);

    def "Check get all students"(){
        when:
            studentService.getStudents();
        then:
            1*mockStudentRepository.findAll();
    }

    def "Check get student by Id"(){
        when:
            studentService.getStudentById(1L)
        then:
            1*mockStudentRepository.findById(_ as Long)
    }
    def "Check get student by Name"(){
        when:
        studentService.getStudentByName("Nidhi")
        then:
        1*mockStudentRepository.findByName(_ as String)
    }
    def "Check get student by Email"(){
        when:
        studentService.getStudentByEmail("prateek@gmail.com")
        then:
        1*mockStudentRepository.findByEmail(_ as String)
    }

    def "Check Post student"(){
        given:
        Student student = new Student();
        student.setId(100L);
        student.setName("Raf");
        student.setAge(29);
        student.setDob(LocalDate.of(1992,8,5));
        student.setEmail("raf@tennis.com");

        when:
        def actualStudent = mockStudentRepository.save(student);
        then:
        assert actualStudent == studentService.addStudent(student);
    }

    def "Check delete student"(){
        when:
        studentService.deleteStudent(1L);
        then:
        1*mockStudentRepository.deleteById(_ as Long);
    }
    def "Check delete student by name"(){
        when:
        studentService.deleteStudentByName("Nidhi");
        then:
        1*mockStudentRepository.deleteByName(_ as String);
    }

}
