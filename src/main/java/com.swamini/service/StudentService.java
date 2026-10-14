package com.swamini.service;

import com.swamini.exception.StudentNotFound;
import com.swamini.model.Student;
import com.swamini.repository.StudentRepository;
import com.swamini.util.InputValidator;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudentForApi(Student student) {
        if (!InputValidator.isValidName(student.getName())) {
            throw new IllegalArgumentException("Invalid name.");
        }
        if (!InputValidator.isValidAge(student.getAge())) {
            throw new IllegalArgumentException("Invalid age.");
        }
        if (!InputValidator.isValidEmail(student.getEmail())) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        return studentRepository.save(student);
    }

    public List<Student> getAllStudentsForApi() {
        return studentRepository.findAll();
    }

    public Student searchByIdForApi(int id) {
        Optional<Student> result = studentRepository.findById(id);
        return result.orElseThrow(() -> new StudentNotFound(id));
    }

    public Student updateStudentForApi(int id, Student student) {
        if (!InputValidator.isValidName(student.getName())) {
            throw new IllegalArgumentException("Invalid name.");
        }
        if (!InputValidator.isValidAge(student.getAge())) {
            throw new IllegalArgumentException("Invalid age.");
        }
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFound(id);
        }
        Student updated = new Student(id, student.getName(), student.getAge(),
                student.getDepartment(), student.getEmail());
        return studentRepository.save(updated);
    }

    public void deleteStudentForApi(int id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFound(id);
        }
        studentRepository.deleteById(id);
    }
}