package com.yaudong.assignment.bookstoreservice.repository;

import com.yaudong.assignment.bookstoreservice.model.EmployeeRole;
import com.yaudong.assignment.bookstoreservice.model.EmployeeRoleEnum;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRoleRepository extends CrudRepository<EmployeeRole, Long> {
    Optional<EmployeeRole> findByName(EmployeeRoleEnum name);
}
