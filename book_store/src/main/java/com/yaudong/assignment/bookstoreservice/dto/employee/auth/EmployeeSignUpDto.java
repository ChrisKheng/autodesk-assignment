package com.yaudong.assignment.bookstoreservice.dto.employee.auth;

import lombok.Data;

@Data
public class EmployeeSignUpDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
