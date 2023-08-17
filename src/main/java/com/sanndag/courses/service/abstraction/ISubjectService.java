package com.sanndag.courses.service.abstraction;

import com.sanndag.courses.model.dto.SubjectDto;
import com.sanndag.courses.model.entity.Subject;

import java.util.List;

public interface ISubjectService {

    void createSubject(Subject subject);
    List<SubjectDto>updateSubject(Subject subject);
    List<SubjectDto> getAll();
    void deleteSubject(Long id);
}
