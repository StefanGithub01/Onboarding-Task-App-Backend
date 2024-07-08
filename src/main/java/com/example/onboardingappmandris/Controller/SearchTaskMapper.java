package com.example.onboardingappmandris.Controller;

import com.example.onboardingappmandris.DTO.SearchTaskDTO;
import com.example.onboardingappmandris.Service.SearchTask;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SearchTaskMapper {
    SearchTask mapToSearchTask(SearchTaskDTO searchTaskDTO);
    SearchTaskDTO mapToSearchTaskDTO(SearchTask searchTask);
}
