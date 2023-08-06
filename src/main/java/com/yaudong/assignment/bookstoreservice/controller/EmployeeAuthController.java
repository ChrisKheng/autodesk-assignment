package com.yaudong.assignment.bookstoreservice.controller;

import com.yaudong.assignment.bookstoreservice.dto.employee.auth.EmployeeLoginDto;
import com.yaudong.assignment.bookstoreservice.dto.employee.auth.EmployeeSignUpDto;
import com.yaudong.assignment.bookstoreservice.model.Employee;
import com.yaudong.assignment.bookstoreservice.model.EmployeeRole;
import com.yaudong.assignment.bookstoreservice.model.EmployeeRoleEnum;
import com.yaudong.assignment.bookstoreservice.repository.EmployeeRepository;
import com.yaudong.assignment.bookstoreservice.repository.EmployeeRoleRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@RequestMapping("/employees/auth")
@RestController
public class EmployeeAuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<String> authenticateUser(HttpServletRequest req, @RequestBody EmployeeLoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()
                )
        );

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(authentication);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return new ResponseEntity<>("User signed-in successfully!.", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerEmployee(@RequestBody EmployeeSignUpDto signUpDto) {
        if (employeeRepository.existsByEmail(signUpDto.getEmail())) {
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        Employee employee = new Employee();
        employee.setFirstName(signUpDto.getFirstName());
        employee.setLastName(signUpDto.getLastName());
        employee.setEmail(signUpDto.getEmail());
        employee.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        EmployeeRole role = employeeRoleRepository.findByName(EmployeeRoleEnum.ADMIN).get();
        employee.setRoles(Collections.singleton(role));

        employeeRepository.save(employee);

        return new ResponseEntity<>("Employee registered successfully!", HttpStatus.OK);
    }
}
