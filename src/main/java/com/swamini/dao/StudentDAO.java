package com.swamini.dao;

import com.swamini.model.Student;
import com.swamini.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;

public class StudentDAO {

    public boolean insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO students (id, name, age, department, email) VALUES (?, ?, ?, ?, ?)";
        // fill in: get connection, prepareStatement, 5 setters, executeUpdate, return rows>0
        Connection conn=DBConnection.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);

        stmt.setInt(1,student.getId());
        stmt.setString(2,student.getName());
        stmt.setInt(3,student.getAge());
        stmt.setString(4,student.getDepartment());
        stmt.setString(5,student.getEmail());

        int rows = stmt.executeUpdate();
        stmt.close();
        return rows > 0;

    }

    public ArrayList<Student> getAllStudents() throws SQLException {
        String sql = "SELECT * FROM students ORDER BY id";

        ArrayList<Student> list = new ArrayList<>();

        Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String department = rs.getString("department");
            String email = rs.getString("email");

            Student s = new Student(id, name, age, department, email);
            list.add(s);
        }

        rs.close();
        stmt.close();

        return list;
    }
    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE id = ?";
        // fill in: setInt(1,id), executeQuery, if rs.next() build+return Student, else return null
        Connection conn=DBConnection.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);
        stmt.setInt(1,id);
        ResultSet rs=stmt.executeQuery();
        if(rs.next()){
            int Studentid = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            String department = rs.getString("department");
            String email = rs.getString("email");

            Student s = new Student(id, name, age, department, email);
            return s;

        }
        else {
            return null;
        }
    }

    public boolean deleteStudent(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";
        Connection con=DBConnection.getConnection();
        PreparedStatement stmt=con.prepareStatement(sql);
        stmt.setInt(1,id);
        int row=stmt.executeUpdate();
        stmt.close();
        return row>0;

        // fill in: setInt, executeUpdate, return rows>0
    }

    public boolean updateStudent(Student student) throws SQLException {
        String sql = "UPDATE students SET name=?, age=?, department=?, email=? WHERE id=?";
        // fill in: 4 setters + id last, executeUpdate, return rows>0
        Connection conn=DBConnection.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql);


        stmt.setString(1,student.getName());
        stmt.setInt(2,student.getAge());
        stmt.setString(3,student.getDepartment());
        stmt.setString(4,student.getEmail());
        stmt.setInt(5,student.getId());
        int row=stmt.executeUpdate();
        stmt.close();
        return row>0;

    }
}
