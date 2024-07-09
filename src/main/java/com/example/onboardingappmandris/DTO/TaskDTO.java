package com.example.onboardingappmandris.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
    private String subject;
    private String dueDate;
    private String status;
    private String assignedUserName;

}
