package com.sanndag.courses.controller;

import com.sanndag.courses.model.entity.Subject;
import com.sanndag.courses.service.abstraction.ISubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Subjects Controller")
@RequestMapping("/api/v1/subjects")
@RestController
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @ApiOperation(value = "Create subject.")
    @PostMapping("/create")
    public ResponseEntity<?> createSubject(@RequestBody Subject subject) {
        subjectService.createSubject(subject);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Get all subjects.")
    @GetMapping("/getall")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(subjectService.getAll());
    }

    @ApiOperation(value = "Delete a subject by id.")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id){
        subjectService.deleteSubject(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update subject information.")
    @PutMapping("/update")
    public ResponseEntity<?> updateSubject(@RequestBody Subject subject){
        subjectService.updateSubject(subject);
        return ResponseEntity.ok().build();

    }

}
