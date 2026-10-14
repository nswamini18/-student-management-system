package com.swamini.service;

import com.swamini.exception.StudentNotFound;
import com.swamini.model.Marks;
import com.swamini.model.Student;
import com.swamini.repository.MarksRepository;
import com.swamini.repository.StudentRepository;
import com.swamini.util.InputValidator;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MarksService {

    private final MarksRepository marksRepository;
    private final StudentRepository studentRepository;

    public MarksService(MarksRepository marksRepository, StudentRepository studentRepository) {
        this.marksRepository = marksRepository;
        this.studentRepository = studentRepository;
    }

    public Marks addMarksForApi(int studentId, Marks marks) {
        if (!InputValidator.isValidMarks(marks.getMarks())) {
            throw new IllegalArgumentException("Marks must be between 0 and 100.");
        }
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFound(studentId));
        marks.setStudent(student);
        return marksRepository.save(marks);
    }

    public List<Marks> getMarksByStudentForApi(int studentId) {
        return marksRepository.findByStudent_Id(studentId);
    }

    public List<String[]> getMarksAnalyticsForApi() {
        List<Object[]> rows = marksRepository.getMarksAnalytics();
        return rows.stream()
                .map(r -> new String[] {
                        String.valueOf(r[0]),
                        String.valueOf(r[1]),
                        String.valueOf(r[2]),
                        String.valueOf(r[3])
                })
                .toList();
    }
}