package com.company;

import java.sql.*;
import java.util.ArrayList;


public class DatabaseHelper {
    public static final String DBURL = "jdbc:sqlite:mydb.sqlite";

    public static void drop() {
        String sql = "drop table courses";

        try (Connection conn = DriverManager.getConnection(DBURL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String sql2 = "drop table courses";

        try (Connection conn = DriverManager.getConnection(DBURL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createNewTable() {
        // SQLite connection string

        String sql = "CREATE TABLE IF NOT EXISTS COURSES(FacultyName text, CourseNumber int, coursename text, "
                + "courseDescription text, credits int, PRIMARY KEY (facultyname, coursenumber))";

        try (Connection conn = DriverManager.getConnection(DBURL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void createTablePrereqs() {
        String sql = "CREATE TABLE IF NOT EXISTS PREREQS(FacultyName1 TEXT, CourseNumber1 INT, FacultyName2 TEXT, CourseNumber2 INT, FOREIGN KEY(FacultyName1, FacultyName2, CourseNumber1, CourseNumber2) REFERENCES COURSES(facultyname, facultyname, coursenumber, coursenumber), PRIMARY KEY(FacultyName1, CourseNumber1))";
        try (Connection conn = DriverManager.getConnection(DBURL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void insertCourse(String facultyName, int courseNumber, String courseName, String courseDescription, float credits) {
        String sql = "INSERT INTO COURSES(FacultyName, CourseNumber, courseName, courseDescription, credits) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DBURL)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, facultyName);
            preparedStatement.setInt(2, courseNumber);
            preparedStatement.setString(3, courseName);
            preparedStatement.setString(4, courseDescription);
            preparedStatement.setFloat(5, credits);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void removeCourse(String facultyName, int courseNumber) {
        String sql = "DELETE FROM COURSES WHERE facultyname = ? AND COURSENUMBER = ?";
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, facultyName);
            preparedStatement.setInt(2, courseNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static void insertPrereq(String facultyName1, int courseNumber1, String facultyName2, int courseNumber2) {
        String sql = "INSERT INTO prereqs(facultyname1, coursenumber1, facultyname2, coursenumber2) VALUES(?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DBURL);
             Statement stmt = conn.createStatement()) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, facultyName1);
            preparedStatement.setInt(2, courseNumber1);
            preparedStatement.setString(3, facultyName2);
            preparedStatement.setInt(4, courseNumber2);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void showTables() {
        String sql = "SELECT * FROM COURSES";
        try (Connection conn = DriverManager.getConnection(DBURL);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("facultyname") + "\t" +
                        rs.getInt("coursenumber"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql2 = "SELECT * FROM prereqs";
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            PreparedStatement psmt = conn.prepareStatement(sql2);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("facultyname1") + "\t" +
                        rs.getInt("coursenumber1") + "\t" +
                        rs.getString("facultyname2") + "\t" +
                        rs.getInt("coursenumber2"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Course> makeCourse() {
        String sql = "SELECT * FROM COURSES";
        try (Connection conn = DriverManager.getConnection(DBURL);
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            ArrayList<Course> result = new ArrayList<>();
            while (rs.next()) {
                result.add(new Course(rs.getString("Coursename"), rs.getString("Facultyname"), rs.getInt("coursenumber"), rs.getFloat("credits"), rs.getString("courseDescription")));

            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static void makePrereqs(Course course, ArrayList<Course> courses) {
        String sql = "SELECT Facultyname2, coursenumber2 FROM prereqs where Facultyname1 = ? and coursenumber1 = ?";
        try (Connection conn = DriverManager.getConnection(DBURL)) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, course.getCourseFaculty());
            pstmt.setInt(2, course.getCourseNumber());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                for (Course c : courses) {
                    Course temp = new Course(rs.getString("Facultyname2"), rs.getInt("coursenumber2"));
                    System.out.println(temp.toString());
                    if (c.equals(temp)) {
                        course.addPrerequisites(c);
                    }
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}