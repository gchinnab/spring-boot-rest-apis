package com.chinna.learn.employees_rest_api.dao;

import com.chinna.learn.employees_rest_api.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
