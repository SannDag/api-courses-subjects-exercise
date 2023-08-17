package com.sanndag.courses.model.mapper;

import com.sanndag.courses.model.dto.CourseDto;
import com.sanndag.courses.model.dto.SubjectsFromCourseDto;
import com.sanndag.courses.model.entity.Course;
import org.springframework.stereotype.Component;


@Component
public class CourseMapper {

    public CourseDto entityToDto(Course course){
        return CourseDto.builder()
                .courseId(course.getCourse_id())
                .name(course.getName())
                .modality(course.getModality())
                .endDate(course.getEndDate())
                .subjectsList(course.getSubjectsList())
                .build();
    }

    public Course DtoToEntity(CourseDto courseDto){
        return Course.builder()
                .course_id(courseDto.getCourseId())
                .name(courseDto.getName())
                .modality(courseDto.getModality())
                .endDate(courseDto.getEndDate())
                .subjectsList(courseDto.getSubjectsList())
                .build();

    }

    public SubjectsFromCourseDto subjectsAndCourseDto (Course course){
        return SubjectsFromCourseDto.builder()
                .courseId(course.getCourse_id())
                .courseName(course.getName())
                .subjectsList(course.getSubjectsList())
                .build();
    }
}
