package com.example.manageequipment.controller;

import com.example.manageequipment.dto.SubjectDto;
import com.example.manageequipment.service.impl.SubjectServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SubjectController {
    @Autowired
    SubjectServiceImpl subjectService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<SubjectDto> createSubject(@Valid @RequestBody SubjectDto subjectDto) {
        return new ResponseEntity<>(subjectService.createSubject(subjectDto) , HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<SubjectDto> updateSubject(@Valid @RequestBody SubjectDto subjectDto,  @PathVariable Long id) {
        return new ResponseEntity<>(subjectService.updateSubject(id, subjectDto) , HttpStatus.CREATED);
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<SubjectDto>> getAllSubject() {
        return new ResponseEntity<>(subjectService.getAllSubject() , HttpStatus.CREATED);
    }
}
