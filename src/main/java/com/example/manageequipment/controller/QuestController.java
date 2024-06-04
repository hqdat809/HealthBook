package com.example.manageequipment.controller;

import com.example.manageequipment.dto.QuestDto;
import com.example.manageequipment.dto.SubjectDto;
import com.example.manageequipment.service.impl.QuestServiceImpl;
import com.example.manageequipment.type.IntegerArrayRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quest")
public class QuestController {

    @Autowired
    QuestServiceImpl questService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<QuestDto> createQuest(@Valid @RequestBody QuestDto questDto) {
        return new ResponseEntity<>(questService.createQuest(questDto) , HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<QuestDto> updateQuest(@Valid @RequestBody QuestDto questDto, @PathVariable Long id) {
        return new ResponseEntity<>(questService.updateQuest(id, questDto) , HttpStatus.OK);
    }

    @GetMapping("/subject/{subjectId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<QuestDto>> getQuestBySubject(@PathVariable Long subjectId) {
        return new ResponseEntity<>(questService.getQuestBySubject(subjectId) , HttpStatus.OK);
    }

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<QuestDto>> getQuestBySubject( @RequestParam(value = "title", defaultValue = "", required = false) String title) {
        return new ResponseEntity<>(questService.getQuestsByTitle(title) , HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<String> deleteQuest(@RequestBody IntegerArrayRequest ids) {
        questService.deleteQuest(ids.getIds());
        return new ResponseEntity<>("Delete quest successfull", HttpStatus.OK);
    }
}
