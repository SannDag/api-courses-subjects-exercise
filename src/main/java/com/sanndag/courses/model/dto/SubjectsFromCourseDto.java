package com.sanndag.courses.model.dto;

import com.sanndag.courses.model.entity.Subject;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectsFromCourseDto {
    private Long courseId;
    private String courseName;
    private List<Subject> subjectsList;

}
