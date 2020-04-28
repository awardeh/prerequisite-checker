package com.company;

import java.util.ArrayList;

import static com.company.DatabaseHelper.*;

public class Main {

    public static void main(String[] args) {
        showTables();
        ArrayList<Course> courses = makeCourse();
        assert courses != null;
        makePrereqs(courses.get(0), courses);
        for (Course c : courses) {
            System.out.println(c.toString());
            System.out.println(c.getPreRequisites());
        }
    }
}
