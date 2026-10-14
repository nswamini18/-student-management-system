package com.swamini.controller;

import com.swamini.model.Marks;
import com.swamini.service.MarksService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/marks")
public class MarksController {

    private final MarksService marksService;

    public MarksController(MarksService marksService) {
        this.marksService = marksService;
    }

    @PostMapping("/student/{studentId}")
    public Marks addMarks(@PathVariable int studentId, @RequestBody Marks marks) {
        return marksService.addMarksForApi(studentId, marks);
    }
    @GetMapping("/student/{studentId}")
    public List<Marks> getMarksByStudent(@PathVariable int studentId) {
        return marksService.getMarksByStudentForApi(studentId);
    }

    @GetMapping("/analytics")
    public List<String[]> getMarksAnalytics() {
        return marksService.getMarksAnalyticsForApi();
    }
}
