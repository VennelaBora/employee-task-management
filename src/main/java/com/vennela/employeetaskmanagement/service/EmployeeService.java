package com.vennela.employeetaskmanagement.service;

import com.vennela.employeetaskmanagement.dto.EmployeeRequest;
import com.vennela.employeetaskmanagement.entity.Employee;
import com.vennela.employeetaskmanagement.exception.DuplicateResourceException;
import com.vennela.employeetaskmanagement.exception.ResourceNotFoundException;
import com.vennela.employeetaskmanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
    }

    @Transactional
    public Employee create(EmployeeRequest request) {
        if (repository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email already exists");
        }
        return repository.save(new Employee(
                request.name(), request.email(), request.department()
        ));
    }

    @Transactional
    public Employee update(Long id, EmployeeRequest request) {
        Employee employee = findById(id);

        repository.findByEmail(request.email())
                .filter(existing -> !existing.getId().equals(id))
                .ifPresent(existing -> {
                    throw new DuplicateResourceException("Email already exists");
                });

        employee.setName(request.name());
        employee.setEmail(request.email());
        employee.setDepartment(request.department());
        return employee;
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
