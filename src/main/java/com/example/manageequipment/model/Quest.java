package com.example.manageequipment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Data
@Table(name = "Quest")
public class Quest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Number shouldn't be null!!")
    private String number;

    @NotNull(message = "Question shouldn't be null!!")
    private String quest;

    private String answer;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject = null;
}
