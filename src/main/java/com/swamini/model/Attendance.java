package com.swamini.model;

public class Attendance {
    private int id;
    private int studentId;
    private String date;    // format: YYYY-MM-DD
    private String status;  // PRESENT or ABSENT

    public Attendance(int id, int studentId,
                      String date, String status) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.status = status;
    }

    public int getId()          { return id; }
    public int getStudentId()   { return studentId; }
    public String getDate()     { return date; }
    public String getStatus()   { return status; }

    @Override
    public String toString() {
        return "| " + studentId + "\t| " + date +
                "\t| " + status + "\t|";
    }
}
