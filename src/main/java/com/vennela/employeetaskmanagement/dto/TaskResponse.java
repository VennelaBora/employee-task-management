package com.vennela.employeetaskmanagement.dto;

import com.vennela.employeetaskmanagement.entity.TaskStatus;

public record TaskResponse(
        Long id,
        String title,
        String description,
        TaskStatus status,
        Long employeeId,
        String employeeName
) {}
