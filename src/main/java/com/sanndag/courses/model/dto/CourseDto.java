package com.sanndag.courses.model.dto;

import com.sanndag.courses.model.entity.Subject;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CourseDto {
    private Long courseId;
    private String name;
    private String modality;
    private LocalDate endDate;
    private List<Subject> subjectsList;
}
