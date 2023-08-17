package com.sanndag.courses.service;

import com.sanndag.courses.exception.SubjectAlreadyExistsException;
import com.sanndag.courses.exception.SubjectNotFoundException;
import com.sanndag.courses.model.dto.SubjectDto;
import com.sanndag.courses.model.entity.Subject;
import com.sanndag.courses.model.mapper.SubjectMapper;
import com.sanndag.courses.repository.ICourseRepository;
import com.sanndag.courses.repository.ISubjectRepository;
import com.sanndag.courses.service.abstraction.ICourseService;
import com.sanndag.courses.service.abstraction.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private ISubjectRepository subjectRepository;

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private ICourseService courseService;


    @Override
    public void createSubject(Subject subject) {
        boolean idSubject = subjectRepository.findById(subject.getSubject_id()).isPresent();

        if(idSubject){
            throw new SubjectAlreadyExistsException("The subject with id " + subject.getSubject_id() + " already exists");
        }

        Optional<Subject> existingSubject = subjectRepository.findByName(subject.getName());
        if (existingSubject.isPresent()) {
            throw new SubjectAlreadyExistsException("Subject with the name '" + subject.getName() + "' already exists.");
        }

        subjectRepository.save(subject);
    }

    @Override
    public List<SubjectDto> updateSubject(Subject subject) {
        Optional<Subject> subOptional = subjectRepository.findById(subject.getSubject_id());
        if(subOptional.isPresent()){
            Subject existingSubject = subOptional.get();

            existingSubject.setName(subject.getName());
            existingSubject.setDescription(subject.getDescription());
            subjectRepository.save(existingSubject);

            SubjectDto subjectDto = subjectMapper.entityToDto(existingSubject);

            return Collections.singletonList(subjectDto);
        } else {
            throw new SubjectNotFoundException("The subject was not found");
        }
    }

    @Override
    public List<SubjectDto> getAll() {
        List<Subject> subjectList = subjectRepository.findAll();
        if(subjectList.isEmpty()){
            throw new SubjectNotFoundException("The subject was not found");
        }

        List<SubjectDto> dtoList = subjectList.stream()
                .map(subjectMapper::entityToDto)
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public void deleteSubject(Long id) {
        Optional<Subject> subOptional = subjectRepository.findById(id);
        if(subOptional.isEmpty()){
            throw new SubjectNotFoundException("The subject with id " + id + " was not found.");
        } else {
            subjectRepository.deleteById(id);
        }
    }
}
