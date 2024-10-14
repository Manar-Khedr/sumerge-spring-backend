package com.sumerge.service;

import com.sumerge.dto.CourseDTO;
import com.sumerge.exception.ResourceNotFoundException;
import com.sumerge.mapper.CourseMapper;
import com.sumerge.repository.CourseRepository;
import com.sumerge.springTask3.classes.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import javax.validation.ValidationException;

@Service
public class CourseService {

    //private static final Logger logger = LoggerFactory.getLogger(CourseService.class);

    //private final CourseRecommender courseRecommender;
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Autowired
    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        //this.courseRecommender = courseRecommender;
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }


    // add course, use coursedto to mapp between course and coursedto
    // Add course
    public CourseDTO addCourse(CourseDTO courseDTO) throws ValidationException{
        String courseName = courseDTO.getCourseName();
        if (courseRepository.findByCourseName(courseName).isPresent()) {
            throw new ValidationException("Course with name " + courseName + " already exists.");
        }
        Course course = courseMapper.mapToCourse(courseDTO);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.mapToCourseDTO(savedCourse);
    }

    // Update course
    public CourseDTO updateCourse(CourseDTO courseDTO) throws ResourceNotFoundException {
        String courseName = courseDTO.getCourseName();
        Course existingCourse = courseRepository.findByCourseName(courseName)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with name: " + courseName));;

        // Update only the necessary fields
        existingCourse.setCourseName(courseDTO.getCourseName());
        existingCourse.setCourseDescription(courseDTO.getCourseDescription());
        existingCourse.setCourseCredit(courseDTO.getCourseCredit());
        // Update other fields as needed

        Course updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.mapToCourseDTO(updatedCourse);
    }

    // Delete course
    public void deleteCourse(String courseName) throws ResourceNotFoundException{
        Course course = courseRepository.findByCourseName(courseName)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with name: " + courseName));
        courseRepository.delete(course);
    }

    // View course
    public CourseDTO viewCourse(String courseName) throws ResourceNotFoundException {
        Course course = courseRepository.findByCourseName(courseName)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with name: " + courseName));
        return courseMapper.mapToCourseDTO(course);
    }

    // View all courses with pagination
    public Page<CourseDTO> viewAllCourses(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return courseRepository.findAll(pageable)
                .map(courseMapper::mapToCourseDTO);
    }
}