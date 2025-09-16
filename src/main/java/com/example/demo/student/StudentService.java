package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }
    
    public List<Student> getStudents() {
        return repository.findAll();
	}

    public Optional<Student> getStudent(Long studentId) {
        // Optional<Student> studentOptional = repository.findStudentByEmail(studentEmail);
        Optional<Student> studentOptional = repository.findById(studentId);
        if (!studentOptional.isPresent()) {
            throw new IllegalStateException("student not found");
        }
        return studentOptional;
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = repository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        repository.save(student);
        //System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = repository.existsById(studentId);
        if (!exists) {
            throw new IllegalStateException("student with id " + studentId + " does not exist");
        }
        repository.deleteById(studentId);
    }
}
