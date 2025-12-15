package com.example;

import java.util.*;

public class App {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("Branch wise Count of Students");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            try {
                switch (choice) {

                    case 1: // Add student
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Semester: ");
                        int sem = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Department: ");
                        String dept = sc.nextLine();

                        Student s1 = new Student(name, sem, dept);
                        dao.addStudent(s1);
                        System.out.println("Student Added Successfully!");
                        break;

                    case 2: // View all students
                        List<Student> list = dao.getAllStudents();
                        System.out.println("\n--- All Students ---");
                        for (Student s : list) {
                            System.out.println(s);
                        }
                        break;

                    case 3: // Update student
                        System.out.print("Enter Student ID to Update: ");
                        int uid = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter New Semester: ");
                        int newSem = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Department: ");
                        String newDept = sc.nextLine();

                        Student s2 = new Student(newName, newSem, newDept);
                        s2.setId(uid);

                        dao.updateStudent(s2);
                        System.out.println("Student Updated Successfully!");
                        break;

                    case 4: // Delete student
                        System.out.print("Enter Student ID to Delete: ");
                        int did = sc.nextInt();
                        dao.deleteStudent(did);
                        System.out.println("Student Deleted Successfully!");
                        break;

                    case 5: // Branch wise count of students
                        Map<String, Integer> branchCount = dao.getBranchWiseCount();
                        System.out.println("\n--- Branch wise Student Count ---");
                        for (Map.Entry<String, Integer> entry : branchCount.entrySet()) {
                            System.out.println("Department: " + entry.getKey() + ", Count: " + entry.getValue());
                        }
                        break;

                    case 6: // Exit
                        System.out.println("Exiting Program...");
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid Choice! Try again.");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
