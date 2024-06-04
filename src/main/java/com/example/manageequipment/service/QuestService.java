package com.example.manageequipment.service;

import com.example.manageequipment.dto.QuestDto;
import com.example.manageequipment.model.Quest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestService {
    QuestDto createQuest(QuestDto questDto);

    QuestDto updateQuest(Long questId, QuestDto questDto);

    void deleteQuest(List<Long> questId);

    List<QuestDto> getQuestBySubject(Long subjectId);

    List<QuestDto> getQuestsByTitle(String title);
}
