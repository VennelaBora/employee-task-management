package com.vennela.employeetaskmanagement.service;

import com.vennela.employeetaskmanagement.dto.TaskRequest;
import com.vennela.employeetaskmanagement.dto.TaskResponse;
import com.vennela.employeetaskmanagement.entity.Employee;
import com.vennela.employeetaskmanagement.entity.Task;
import com.vennela.employeetaskmanagement.entity.TaskStatus;
import com.vennela.employeetaskmanagement.exception.ResourceNotFoundException;
import com.vennela.employeetaskmanagement.repository.EmployeeRepository;
import com.vennela.employeetaskmanagement.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public TaskService(TaskRepository taskRepository, EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<TaskResponse> findAll(TaskStatus status, Long employeeId) {
        List<Task> tasks;
        if (status != null) {
            tasks = taskRepository.findByStatus(status);
        } else if (employeeId != null) {
            tasks = taskRepository.findByEmployeeId(employeeId);
        } else {
            tasks = taskRepository.findAll();
        }
        return tasks.stream().map(this::toResponse).toList();
    }

    public TaskResponse findById(Long id) {
        return toResponse(taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id)));
    }

    @Transactional
    public TaskResponse create(TaskRequest request) {
        Employee assignedEmployee = employee(request.employeeId());
        Task task = new Task(
                request.title(),
                request.description(),
                request.status(),
                assignedEmployee
        );
        return toResponse(taskRepository.save(task));
    }

    @Transactional
    public TaskResponse update(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id));

        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setStatus(request.status());
        task.setEmployee(employee(request.employeeId()));
        return toResponse(task);
    }

    @Transactional
    public void delete(Long id) {
        taskRepository.delete(taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found: " + id)));
    }

    private Employee employee(Long id) {
        if (id == null) {
            return null;
        }
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
    }

    private TaskResponse toResponse(Task task) {
        Employee employee = task.getEmployee();
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                employee == null ? null : employee.getId(),
                employee == null ? null : employee.getName()
        );
    }
}
