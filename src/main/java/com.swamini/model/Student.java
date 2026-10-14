package com.swamini.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String department;
    private String email;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Marks> marksList;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Attendance> attendanceList;

    public Student() {}

    public Student(int id, String name, int age,
                   String department, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.email = email;
    }

    // Getters
    public int getId()           { return id; }
    public String getName()      { return name; }
    public int getAge()          { return age; }
    public String getDepartment(){ return department; }
    public String getEmail()     { return email; }

    // Setters
    public void setId(int id)           { this.id = id; }
    public void setName(String name)           { this.name = name; }
    public void setAge(int age)                { this.age = age; }
    public void setDepartment(String dept)     { this.department = dept; }
    public void setEmail(String email)         { this.email = email; }

    @Override
    public String toString() {
        return "| " + id + "\t| " + name + "\t| " + age +
                "\t| " + department + "\t| " + email + "\t|";
    }
}