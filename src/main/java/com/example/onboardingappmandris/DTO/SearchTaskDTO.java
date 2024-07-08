package com.example.onboardingappmandris.DTO;

import com.example.onboardingappmandris.Entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class SearchTaskDTO {
    private Long userId;
    private String subject;
    private LocalDate dueDateAfter;
    private String status;
}
