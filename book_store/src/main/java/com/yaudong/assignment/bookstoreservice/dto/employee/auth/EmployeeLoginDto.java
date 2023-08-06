package com.yaudong.assignment.bookstoreservice.dto.employee.auth;

import lombok.Data;

@Data
public class EmployeeLoginDto {
    private String email;
    private String password;
}
