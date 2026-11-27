package com.vennela.employeetaskmanagement.dto;

import com.vennela.employeetaskmanagement.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TaskRequest(
        @NotBlank String title,
        String description,
        @NotNull TaskStatus status,
        Long employeeId
) {}
