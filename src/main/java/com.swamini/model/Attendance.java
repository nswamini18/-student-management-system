package com.swamini.model;

import jakarta.persistence.*;

@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String date;
    private String status;

    public Attendance() {}

    public Attendance(int id, Student student, String date, String status) {
        this.id = id;
        this.student = student;
        this.date = date;
        this.status = status;
    }

    public int getId() { return id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}