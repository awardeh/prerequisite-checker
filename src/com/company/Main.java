package com.company;

import java.util.ArrayList;

import static com.company.DatabaseHelper.*;

public class Main {

    public static void main(String[] args) {
        showTables();
        ArrayList<Course> courses = makeCourse();
        System.out.println(courses);
    }
}
