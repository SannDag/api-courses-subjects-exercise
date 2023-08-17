package com.sanndag.courses.model.mapper;

import com.sanndag.courses.model.dto.SubjectDto;
import com.sanndag.courses.model.entity.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public SubjectDto entityToDto(Subject subject){
        return SubjectDto.builder()
                .subjectId(subject.getSubject_id())
                .name(subject.getName())
                .description(subject.getDescription())
                .build();

    }


}
