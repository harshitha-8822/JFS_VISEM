package com.example;

import java.sql.*;
import java.util.*;

public class StudentDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/skillnext_db";
    private static final String USER = "root";
    private static final String PASSWORD = "thakur1506"; // change this

    // Add students
    public void addStudent(Student stu) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "INSERT INTO student (name, sem, departement) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, stu.getName());
        stmt.setInt(2, stu.getSem());
        stmt.setString(3, stu.getDepartement());
        stmt.executeUpdate();
        conn.close();
    }

    // Fetch all students
    public List<Student> getAllStudents() throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
  String sql = "SELECT * FROM student";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        List<Student> list = new ArrayList<>();
        while (rs.next()) {
            Student s = new Student();
            s.setId(rs.getInt("id"));
            s.setName(rs.getString("name"));
            s.setSem(rs.getInt("sem"));
            s.setDepartement(rs.getString("departement"));
            list.add(s);
        }
        conn.close();
        return list;
    }

    // Delete students
    public void deleteStudent(int id) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "DELETE FROM student WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        conn.close();
    }
//branch wise count of students
    public Map<String, Integer> getBranchWiseCount() {
    Map<String, Integer> map = new HashMap<>();

    String sql = "SELECT dept, COUNT(*) FROM Student GROUP BY dept";

    try (
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()
    ) {
        while (rs.next()) {
            map.put(rs.getString(1), rs.getInt(2));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return map;
}



    // Update student
    public void updateStudent(Student stu) throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql = "UPDATE student SET name=?, sem=?, departement=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, stu.getName());
        stmt.setInt(2, stu.getSem());
        stmt.setString(3, stu.getDepartement());
        stmt.setInt(4, stu.getId());
        stmt.executeUpdate();
        conn.close();
    }
}
