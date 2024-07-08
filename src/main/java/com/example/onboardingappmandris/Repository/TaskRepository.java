package com.example.onboardingappmandris.Repository;

import com.example.onboardingappmandris.Entity.Status;
import com.example.onboardingappmandris.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<List<Task>> findByAssignedId(Long id);

    @Query("SELECT t FROM Task t WHERE " +
            "(:userId IS NULL OR t.assigned.id = :userId) AND " +
            "(:subject IS NULL OR t.subject LIKE %:subject%) AND " +
            "(:dueDateAfter IS NULL OR t.dueDate > :dueDateAfter) AND " +
            "(:status IS NULL OR t.status = :status)")
    List<Task> searchTasks(@Param("userId") Long userId,
                           @Param("subject") String subject,
                           @Param("dueDateAfter") LocalDate dueDateAfter,
                           @Param("status") Status status);
}
