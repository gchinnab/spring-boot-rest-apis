package com.chinna.learn.employees_rest_api.dao;

import com.chinna.learn.employees_rest_api.entity.Employee;

import java.util.List;

public interface EmployeeDAO {

    List<Employee> findAll();

    Employee findById(long theId);

    Employee save(Employee theEmployee);

    void deleteById(long theId);

}
