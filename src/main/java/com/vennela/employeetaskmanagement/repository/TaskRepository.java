package com.vennela.employeetaskmanagement.repository;

import com.vennela.employeetaskmanagement.entity.Task;
import com.vennela.employeetaskmanagement.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByEmployeeId(Long employeeId);
}
