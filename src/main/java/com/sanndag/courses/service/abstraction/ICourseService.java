package com.sanndag.courses.service.abstraction;

import com.sanndag.courses.model.dto.CourseDto;
import com.sanndag.courses.model.dto.SubjectsFromCourseDto;
import com.sanndag.courses.model.entity.Course;

import java.util.List;

public interface ICourseService {

    void createCourse(Course course);
    List<CourseDto> getAll();
    List<CourseDto>getAllBySimilarName(String name);
    List<SubjectsFromCourseDto>getAllSubjectsFromCourse(Long courseId);
    void deleteCourse(Long id);
    List<CourseDto> updateCourse(Course course);


}
