package com.sumerge.repository;


import com.sumerge.springTask3.classes.Course;

import java.util.List;

public interface CourseRepository {

    void addCourse(Course course);
    void updateCourse(Course course);
    void deleteCourse(Course course);
    Course viewCourse(int courseId);
    List<Course> viewAllCourses();
}
