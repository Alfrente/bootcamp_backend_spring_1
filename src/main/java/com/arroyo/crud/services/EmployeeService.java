package com.arroyo.crud.services;

import com.arroyo.crud.modls.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Iterable<Employee> findAll();
    Page<Employee> findAll(Pageable pageable);
    List<Employee> search(String q);
    Optional<Employee> findOne(Long id);
    void save(Employee employee);
    void delete(Long id);
}
