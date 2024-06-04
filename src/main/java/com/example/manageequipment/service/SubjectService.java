package com.example.manageequipment.service;

import com.example.manageequipment.dto.SubjectDto;
import com.example.manageequipment.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {

    SubjectDto createSubject(SubjectDto subjectDto);

    SubjectDto updateSubject(Long subjectId, SubjectDto subjectDto);

    List<SubjectDto> getAllSubject();

}
