package com.swamini.model;

import jakarta.persistence.*;

@Entity
@Table(name = "marks")
public class Marks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String subject;
    private int marks;

    public Marks() {}

    public Marks(int id, Student student, String subject, int marks) {
        this.id = id;
        this.student = student;
        this.subject = subject;
        this.marks = marks;
    }

    public int getId() { return id; }
    public Student getStudent() { return student; }
    public void setStudent(Student student) { this.student = student; }
    public String getSubject() { return subject; }
    public int getMarks() { return marks; }
}