package com.sumerge.service;


import com.sumerge.dto.CourseDTO;
import com.sumerge.mapper.CourseMapper;
import com.sumerge.repository.CourseRepository;
import com.sumerge.springTask3.classes.Course;
import com.sumerge.springTask3.implementation.CourseRecommender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    //private final CourseRecommender courseRecommender;
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseService( CourseRepository courseRepository, CourseMapper courseMapper) {
        //this.courseRecommender = courseRecommender;
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }


    // add course, use coursedto to mapp between course and coursedto
    public CourseDTO addCourse(CourseDTO courseDTO) {
        logger.info("Adding CourseDTO: {}", courseDTO);
        Course course = courseMapper.mapToCourse(courseDTO);
        logger.info("Mapped Course: {}", course);
        Course savedCourse = courseRepository.save(course);
        logger.info("Saved Course: {}", savedCourse);
        return courseMapper.mapToCourseDTO(savedCourse);
    }

    public CourseDTO updateCourse(CourseDTO courseDTO) {
        Course course = courseMapper.mapToCourse(courseDTO);
        Course updatedCourse = courseRepository.save(course);
        return courseMapper.mapToCourseDTO(updatedCourse);
    }

    public void deleteCourse(int courseId) {
        courseRepository.deleteById(courseId);
    }

    public CourseDTO viewCourse(int courseId) {
        return courseRepository.findById(courseId)
                .map(courseMapper::mapToCourseDTO)
                .orElse(null);
    }

    public Page<CourseDTO> viewAllCourses(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findAll(pageable)
                .map(courseMapper::mapToCourseDTO);
    }


}