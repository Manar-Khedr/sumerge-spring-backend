package com.sumerge.mapper;

import com.sumerge.dto.CourseDTO;
import com.sumerge.springTask3.classes.Course;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;
@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    Course mapToCourse(CourseDTO courseDTO);
    CourseDTO mapToCourseDTO(Course course);
}