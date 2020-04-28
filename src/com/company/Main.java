package com.company;

import java.util.ArrayList;

import static com.company.DatabaseHelper.*;

public class Main {

    public static void main(String[] args) {

        ArrayList<Course> courses = makeCourses();
        assert courses != null;
        for (Course c : courses) {
            makePrereqs(c, courses);
            makePostreqs(c, courses);
            System.out.println(c.toString());
            System.out.println(c.getPrerequisites());
            System.out.println(c.getPostrequisites());
    }
    }
}

