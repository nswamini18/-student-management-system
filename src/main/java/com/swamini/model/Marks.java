package com.swamini.model;

public class Marks {
    private int id;
    private int studentId;
    private String subject;
    private int marks;

    public Marks(int id, int studentId,
                 String subject, int marks) {
        this.id = id;
        this.studentId = studentId;
        this.subject = subject;
        this.marks = marks;
    }

    public int getId()          { return id; }
    public int getStudentId()   { return studentId; }
    public String getSubject()  { return subject; }
    public int getMarks()       { return marks; }

    @Override
    public String toString() {
        return "| " + studentId + "\t| " + subject +
                "\t| " + marks + "\t|";
    }
}
