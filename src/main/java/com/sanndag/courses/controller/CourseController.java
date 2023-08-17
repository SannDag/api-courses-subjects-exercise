package com.sanndag.courses.controller;

import com.sanndag.courses.model.entity.Course;
import com.sanndag.courses.service.abstraction.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Api(value = "Courses Controller")
@RequestMapping("/api/v1/courses")
@RestController
public class CourseController {


    private final ICourseService courseService;

    @ApiOperation(value = "Create a course.")
    @PostMapping("/create")
    public ResponseEntity<?> createCourse(@RequestBody Course course){
        courseService.createCourse(course);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Get all courses.")
    @GetMapping("/getall")
    public ResponseEntity<?> getAllCoursesById(){
        return ResponseEntity.ok().body(courseService.getAll());
    }

    @ApiOperation(value = "Get all courses by name or similar word.")
    @GetMapping("/getsimilarname/{name}")
    public ResponseEntity<?> getAllCoursesByName(@PathVariable String name){
        return ResponseEntity.ok(courseService.getAllBySimilarName(name));
    }

    @ApiOperation(value = "Get all subjects from a specified course.")
    @GetMapping("/getallsubjects/{courseId}")
    public ResponseEntity<?> getSubjectsFromCourse(@PathVariable Long courseId){
        return ResponseEntity.ok().body(courseService.getAllSubjectsFromCourse(courseId));
    }

    @ApiOperation(value = "Delete a course by id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id){
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Update course information.")
    @PutMapping("/update")
    public ResponseEntity<?> updateCourse(@RequestBody Course course){
        courseService.updateCourse(course);
        return ResponseEntity.ok().build();
    }
}
