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

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setDueDateAfter(LocalDate dueDateAfter) {
        this.dueDateAfter = dueDateAfter;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

