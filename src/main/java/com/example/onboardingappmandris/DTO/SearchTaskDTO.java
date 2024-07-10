package com.example.onboardingappmandris.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SearchTaskDTO {
    private Long userId;
    private String subject;
    private LocalDate dueDateAfter;
    private String status;
}
