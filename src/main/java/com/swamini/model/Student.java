package com.swamini.model;

public class Student {
    private int id;
    private String name;
    private int age;
    private String department;
    private String email;

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
