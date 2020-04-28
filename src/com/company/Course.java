package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Course {
    private String courseName;
    private String courseFaculty;
    private int courseNumber;
    private float credits;
    private String description;
    Map<Course, Course> prereqs = new HashMap<>();
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private ArrayList<Course> preRequisites;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseFaculty() {
        return courseFaculty;
    }

    public void setCourseFaculty(String courseFaculty) {
        this.courseFaculty = courseFaculty;
    }

    public ArrayList<String> getPreRequisites() {
        return preRequisites;
    }

    public void setPreRequisites(ArrayList<Course> preRequisites) {
        this.preRequisites = preRequisites;
    }
    public void addPrerequisites(Course prereq){
        this.preRequisites.add(prereq);
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }

    public Course(String courseName, String courseFaculty, int courseNumber, float credits, String description) {
        this.courseName = courseName;
        this.courseFaculty = courseFaculty;
        this.courseNumber = courseNumber;
        this.credits = credits;
        this.description = description;
    }

    @Override
    public String toString(){
        return this.courseFaculty + this.courseNumber;
    }
}
