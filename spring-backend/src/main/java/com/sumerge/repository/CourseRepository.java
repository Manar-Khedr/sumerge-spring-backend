package com.sumerge.repository;


import com.sumerge.springTask3.classes.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
// extends JPA automatically provides implementations for common database operations, so no need for CourseRepositoryImpl
public interface CourseRepository extends JpaRepository<Course, Integer>{
    Optional<Course> findByCourseName(String courseName);

}