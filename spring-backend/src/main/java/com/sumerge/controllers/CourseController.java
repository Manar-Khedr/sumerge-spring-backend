package com.sumerge.controllers;

import com.sumerge.dto.CourseDTO;
import com.sumerge.exception.ResourceNotFoundException;
import com.sumerge.service.CourseService;
import com.sumerge.springTask3.classes.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private CourseService courseService;

    // Bean constructor
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // add course --> post mapping
    @PostMapping
    public ResponseEntity<CourseDTO> addCourse(@RequestBody CourseDTO courseDTO) {
        try {
            CourseDTO createdCourse = courseService.addCourse(courseDTO);
            return new ResponseEntity<>(createdCourse, HttpStatus.OK);
        } catch (ValidationException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    // view course by id --> get mapping
    @GetMapping("/{courseName}")
    public ResponseEntity<CourseDTO> viewCourse(@PathVariable String courseName) {
        try{
            CourseDTO course = courseService.viewCourse(courseName);
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // update course --> put mapping
    @PutMapping("/{courseName}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable String courseName, @RequestBody CourseDTO courseDTO) {
        try{
            courseDTO.setCourseName(courseName);
            CourseDTO updatedCourse = courseService.updateCourse(courseDTO);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch( ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // delete course by id --> delete mapping
    @DeleteMapping("/{courseName}")
    public ResponseEntity<Void> deleteCourse(@PathVariable String courseName) {
        try{
            courseService.deleteCourse(courseName);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch(ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // view all courses using paging -- > get mapping
    public ResponseEntity<Page<CourseDTO>> viewAllCourses(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Page<CourseDTO> authors = courseService.viewAllCourses(page, size);
        return new ResponseEntity<>(authors, HttpStatus.OK);
    }
}