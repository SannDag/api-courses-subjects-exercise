package com.sanndag.courses.model.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SubjectDto {

    private Long subjectId;
    private String name;
    private String description;
}
