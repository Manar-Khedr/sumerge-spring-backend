package com.sumerge.implementation;

import com.sumerge.springTask3.implementation.CourseRecommender;
import org.springframework.stereotype.Component;
import com.sumerge.springTask3.classes.Course;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseRecommenderImpl1 implements CourseRecommender {

    @Override
    public List<Course> recommendedCourses() {
        List<Course> courseList = new ArrayList<>();
        courseList.add(new Course("Java Basics", 1, "Introduction to Java", 3));
        courseList.add(new Course("Database Basics", 2, "Introduction to Database", 3 ));

        return courseList;
    }
}
