package com.sanndag.courses.service;

import com.sanndag.courses.exception.CourseAlreadyExistsException;
import com.sanndag.courses.exception.CourseNotFoundException;
import com.sanndag.courses.exception.SubjectAlreadyExistsException;
import com.sanndag.courses.model.dto.CourseDto;
import com.sanndag.courses.model.dto.SubjectsFromCourseDto;
import com.sanndag.courses.model.entity.Course;
import com.sanndag.courses.model.entity.Subject;
import com.sanndag.courses.model.mapper.CourseMapper;
import com.sanndag.courses.repository.ICourseRepository;
import com.sanndag.courses.repository.ISubjectRepository;
import com.sanndag.courses.service.abstraction.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private ISubjectRepository subjectRepository;

    @Override
    public void createCourse(Course course) {
        Optional<Course> existCourse = courseRepository.findByName(course.getName());

        if(existCourse.isPresent()){
            throw new CourseAlreadyExistsException("The course with id " + course.getName() + " already exists.");
        }
        courseRepository.save(course);
    }

    @Override
    public List<CourseDto> getAll() {
        List<Course> courses = courseRepository.findAll();
        if(courses.isEmpty()){
            throw new CourseNotFoundException("The course was not found.");
        }

        List<CourseDto> courseDto = courses.stream()
                .map(course -> courseMapper.entityToDto(course))
                .collect(Collectors.toList());

        return courseDto;
    }

    @Override
    public List<CourseDto> getAllBySimilarName(String name) {
        List<Course> courses = courseRepository.findBySimilarName(name);
        if(courses.isEmpty()){
            throw new CourseNotFoundException("The course with name " + name + " was not found.");
        }

        List<CourseDto> courseDto = courses.stream()
                .map(course -> courseMapper.entityToDto(course))
                .collect(Collectors.toList());

        return courseDto;
    }

    @Override
    public List<SubjectsFromCourseDto> getAllSubjectsFromCourse(Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);

        if(course.isPresent()){
            List<SubjectsFromCourseDto> subjectsAndCourseDto = course.stream()
                    .map(subcour -> courseMapper.subjectsAndCourseDto(subcour))
                    .collect(Collectors.toList());

            return subjectsAndCourseDto;
        }
        throw new CourseNotFoundException("The course with id " + courseId + " doesn't exist.");
    }

    @Override
    public void deleteCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);

        course.ifPresent(c -> courseRepository.delete(c));

        if (course.isEmpty()) {
            throw new CourseNotFoundException("The course with id " + id + " doesn't exist.");
        }
    }

    @Override
    public List<CourseDto> updateCourse(Course course) {
        Optional<Course> optionalCourse = courseRepository.findById(course.getCourse_id());
        if (optionalCourse.isPresent()){
            Course existingCourse = optionalCourse.get();

            existingCourse.setName(course.getName());
            existingCourse.setModality(course.getModality());
            existingCourse.setEndDate(course.getEndDate());

            for (Subject subject : course.getSubjectsList()) {
                boolean subjectExists = existingCourse.getSubjectsList().stream()
                        .anyMatch(existingSubject -> existingSubject.getSubject_id().equals(subject.getSubject_id()));

                if (!subjectExists) {
                    existingCourse.getSubjectsList().add(subject);
                } else {

                    throw new SubjectAlreadyExistsException("Subject with ID " + subject.getSubject_id() + " already exists in the course.");
                }
            }

            courseRepository.save(existingCourse);

            CourseDto courseDto = courseMapper.entityToDto(existingCourse);

            return Collections.singletonList(courseDto);

        } else {
            throw new CourseNotFoundException("The course was not found.");
        }
    }


}
