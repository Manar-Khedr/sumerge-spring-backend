package com.sumerge.springTask3.implementation;

import com.sumerge.springTask3.classes.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseRecommenderImpl2 implements CourseRecommender{

    @Override
    public List<Course> recommendedCourses() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Course 3"));
        courseList.add(new Course("Course 4"));

        return courseList;
    }
}
