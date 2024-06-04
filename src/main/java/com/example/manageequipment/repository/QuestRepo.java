package com.example.manageequipment.repository;

import com.example.manageequipment.model.Quest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestRepo extends JpaRepository<Quest, Long> {
    List<Quest> findBySubjectId(Long subjectId, Sort sort);

    List<Quest> findByQuestContaining(String title, Sort sort);
}
