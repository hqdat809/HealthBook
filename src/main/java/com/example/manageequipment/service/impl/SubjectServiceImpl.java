package com.example.manageequipment.service.impl;

import com.example.manageequipment.dto.SubjectDto;
import com.example.manageequipment.model.Quest;
import com.example.manageequipment.model.Subject;
import com.example.manageequipment.repository.SubjectRepo;
import com.example.manageequipment.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepo subjectRepo;

    SubjectDto mapToDto(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());

        return subjectDto;
    }

    @Override
    public SubjectDto createSubject(SubjectDto subjectDto) {

        Subject subject = new Subject();

        subject.setName(subjectDto.getName());

        Subject newSubject = subjectRepo.save(subject);
        return mapToDto(newSubject);
    }

    @Override
    public SubjectDto updateSubject(Long subjectId, SubjectDto subjectDto) {
        Subject subject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "subjectId is not found "+ subjectId));

        if (subjectDto.getName() != null && !subjectDto.getName().equals(subject.getName())) {
            subject.setName(subjectDto.getName());
        }

        Subject updatedSubject = subjectRepo.save(subject);

        return mapToDto(updatedSubject);
    }

    @Override
    public List<SubjectDto> getAllSubject() {

        List<Subject> subjectList = subjectRepo.findAll();
        List<SubjectDto> subjectDtoList = new ArrayList<>();

        subjectList.forEach((sub) -> subjectDtoList.add(mapToDto(sub)));

        return subjectDtoList;
    }
}
