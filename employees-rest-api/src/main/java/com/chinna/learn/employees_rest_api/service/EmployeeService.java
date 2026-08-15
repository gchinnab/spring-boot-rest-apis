package com.chinna.learn.employees_rest_api.service;

import com.chinna.learn.employees_rest_api.entity.Employee;
import com.chinna.learn.employees_rest_api.request.EmployeeRequest;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAll();

    Employee findById(long theId);

    Employee save(EmployeeRequest employeeRequest);

    Employee update(long id, EmployeeRequest employeeRequest);

    Employee convertToEmployee(long id, EmployeeRequest employeeRequest);

    void deleteById(long theId);
}
