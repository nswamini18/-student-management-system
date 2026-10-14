package com.swamini.controller;

import com.swamini.model.Attendance;
import com.swamini.service.AttendanceService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/student/{studentId}")
    public Attendance markAttendance(@PathVariable int studentId, @RequestBody Attendance attendance) {
        return attendanceService.markAttendanceForApi(studentId, attendance);
    }
    @GetMapping("/student/{studentId}")
    public List<Attendance> getAttendance(@PathVariable int studentId) {
        return attendanceService.getAttendanceForApi(studentId);
    }

    @GetMapping("/student/{studentId}/percentage")
    public Map<String, Object> getAttendancePercentage(@PathVariable int studentId) {
        return attendanceService.getAttendancePercentageForApi(studentId);
    }
}
