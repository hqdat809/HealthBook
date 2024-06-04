package com.example.manageequipment.service.impl;

import com.example.manageequipment.dto.QuestDto;
import com.example.manageequipment.model.Quest;
import com.example.manageequipment.model.Subject;
import com.example.manageequipment.model.User;
import com.example.manageequipment.repository.QuestRepo;
import com.example.manageequipment.repository.SubjectRepo;
import com.example.manageequipment.service.QuestService;
import com.google.api.gax.rpc.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestServiceImpl implements QuestService {

    @Autowired
    QuestRepo questRepo;

    @Autowired
    SubjectRepo subjectRepo;

    public QuestDto convertDTO(Quest quest) {
        QuestDto questDto = new QuestDto();
        questDto.setId(quest.getId());
        questDto.setNumber(quest.getNumber());
        questDto.setQuest(quest.getQuest());
        questDto.setAnswer(quest.getAnswer());
        questDto.setSubjectId(quest.getSubject().getId());

        return questDto;
    }

    @Override
    public QuestDto createQuest(QuestDto questDto) {
        Quest newQuest = new Quest();

        newQuest.setNumber(questDto.getNumber());
        newQuest.setAnswer(questDto.getAnswer());
        newQuest.setQuest(questDto.getQuest());

        Subject subject = subjectRepo.findById(questDto.getSubjectId())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubjectId is not found "+ questDto.getSubjectId()));

        newQuest.setSubject(subject);

        Quest createdQuest = questRepo.save(newQuest);
        return convertDTO(createdQuest);
    }



    @Override
    public QuestDto updateQuest(Long questId, QuestDto questDto) {
        Quest quest = questRepo.findById(questId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "QuestId is not found "+ questId));

        if (questDto.getNumber() != null && !questDto.getNumber().equals(quest.getNumber())) {
            quest.setNumber(questDto.getNumber());
        }

        if (questDto.getAnswer() != null && !questDto.getAnswer().equals(quest.getAnswer())) {
            quest.setAnswer(questDto.getAnswer());
        }

        if (questDto.getQuest() != null && !questDto.getQuest().equals(quest.getQuest())) {
            quest.setQuest(questDto.getQuest());
        }

        if (questDto.getSubjectId() != null && !questDto.getSubjectId().equals(quest.getSubject().getId())) {
            Subject newSubject = subjectRepo.findById(questDto.getSubjectId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubjectId is not found "+ questDto.getSubjectId()));

            quest.setSubject(newSubject);
        }

        Quest updatedQuest = questRepo.save(quest);

        return convertDTO(updatedQuest);
    }

    @Override
    public void deleteQuest(List<Long> questIds) {
        questIds.forEach(id -> {
            Quest quest = questRepo.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid quest id "+ id));

            questRepo.delete(quest);
        });
    }

    @Override
    public List<QuestDto> getQuestBySubject(Long subjectId) {

        List<Quest> questList = questRepo.findBySubjectId(subjectId, Sort.by(Sort.Direction.ASC, "number"));
        List<QuestDto> questDtoList = new ArrayList<>();

        questList.forEach((quest -> questDtoList.add(convertDTO(quest))));

        return questDtoList;
    }

    @Override
    public List<QuestDto> getQuestsByTitle(String title) {
        List<Quest> questList = questRepo.findByQuestContaining(title, Sort.by(Sort.Direction.ASC, "number"));
        List<QuestDto> questDtoList = new ArrayList<>();

        questList.forEach((quest -> questDtoList.add(convertDTO(quest))));

        return questDtoList;
    }
}
