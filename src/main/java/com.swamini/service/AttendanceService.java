package com.swamini.service;

import com.swamini.exception.StudentNotFound;
import com.swamini.model.Attendance;
import com.swamini.model.Student;
import com.swamini.repository.AttendanceRepository;
import com.swamini.repository.StudentRepository;
import com.swamini.util.InputValidator;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;

    public AttendanceService(AttendanceRepository attendanceRepository, StudentRepository studentRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
    }

    public Attendance markAttendanceForApi(int studentId, Attendance attendance) {
        if (!InputValidator.isValidDate(attendance.getDate())) {
            throw new IllegalArgumentException("Invalid date. Use YYYY-MM-DD format.");
        }
        String status = attendance.getStatus();
        if (!status.equalsIgnoreCase("PRESENT") && !status.equalsIgnoreCase("ABSENT")) {
            throw new IllegalArgumentException("Status must be PRESENT or ABSENT.");
        }
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFound(studentId));
        attendance.setStudent(student);
        attendance.setStatus(status.toUpperCase());
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceForApi(int studentId) {
        return attendanceRepository.findByStudent_Id(studentId);
    }

    public Map<String, Object> getAttendancePercentageForApi(int studentId) {
        List<Attendance> records = attendanceRepository.findByStudent_Id(studentId);
        long present = records.stream().filter(a -> a.getStatus().equalsIgnoreCase("PRESENT")).count();
        int total = records.size();

        Map<String, Object> summary = new HashMap<>();
        summary.put("totalDays", total);
        summary.put("present", present);
        summary.put("absent", total - present);
        summary.put("percentage", total == 0 ? 0.0 : (present * 100.0) / total);
        return summary;
    }
}