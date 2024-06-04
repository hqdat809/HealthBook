package com.example.manageequipment.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestDto {
    private Long id;
    private String number;
    private String quest;
    private String answer;
    private Long subjectId;
}
