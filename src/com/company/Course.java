package com.company;

import java.util.ArrayList;

public class Course {
    private String courseName;
    private String courseFaculty;
    private int courseNumber;
    private float credits;
    private String description;
    private ArrayList<Course> prerequisites = new ArrayList<>();
    private ArrayList<Course> postrequisites = new ArrayList<>();

    public Course(String faculty, int number) {
        this.courseFaculty = faculty;
        this.courseNumber = number;
    }
    public Course(String courseName, String courseFaculty, int courseNumber, float credits, String description) {
        this.courseName = courseName;
        this.courseFaculty = courseFaculty;
        this.courseNumber = courseNumber;
        this.credits = credits;
        this.description = description;
    }

    public ArrayList<Course> getPostrequisites() {
        return postrequisites;
    }

    public void setPostrequisites(ArrayList<Course> postrequisites) {
        this.postrequisites = postrequisites;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public ArrayList<Course> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(ArrayList<Course> preRequisites) {
        this.prerequisites = preRequisites;
    }

    public void addPrerequisites(Course prereq) {
        this.prerequisites.add(prereq);
    }

    public void addPostrequisites(Course postreq) {
        this.postrequisites.add(postreq);
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

    @Override
    public String toString() {
        return this.courseFaculty + this.courseNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this.getClass() != other.getClass()) {
            return false;
        }

        if (!this.courseFaculty.equals(((Course) other).courseFaculty)) {
            return false;
        }

        return this.courseNumber == ((Course) other).courseNumber;


    }
}
