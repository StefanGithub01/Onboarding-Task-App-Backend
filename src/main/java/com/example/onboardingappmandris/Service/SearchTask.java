package com.example.onboardingappmandris.Service;

import com.example.onboardingappmandris.Entity.Status;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class SearchTask {
    private Long userId;
    private String subject;
    private LocalDate dueDateAfter;
    private Status status;
}
