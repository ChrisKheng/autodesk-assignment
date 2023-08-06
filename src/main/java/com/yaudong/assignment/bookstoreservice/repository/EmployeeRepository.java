package com.yaudong.assignment.bookstoreservice.repository;

import com.yaudong.assignment.bookstoreservice.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
    Boolean existsByEmail(String email);
}
