package com.example.manageequipment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubjectDto {
    private Long id;

    @NotNull(message = "Name of subject shouldn't be null!!")
    private String name;
}
