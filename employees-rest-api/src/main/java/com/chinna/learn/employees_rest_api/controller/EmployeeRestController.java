package com.chinna.learn.employees_rest_api.controller;

import com.chinna.learn.employees_rest_api.dao.EmployeeDAO;
import com.chinna.learn.employees_rest_api.entity.Employee;
import com.chinna.learn.employees_rest_api.request.EmployeeRequest;
import com.chinna.learn.employees_rest_api.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Rest API Endpoints", description = "Operations related to employees")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Operation(summary = "Get All Employess", description = "Retrieve a list of all employees")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping()
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @Operation(summary = "Get Employee by Id", description = "Retrieve employee by employeeId")
    @GetMapping("/{employeeId}")
    public Employee getEmployee(@Parameter(description = "Id of th Employee") @PathVariable @Min(value = 1 ) long employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        return theEmployee;
    }

    @Operation(summary = "Create a Nee Employee", description = "Add a new employee to Database")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Employee addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest){
        return employeeService.save(employeeRequest);
    }

    @Operation(summary = "Update an employee", description = "update the details of employee")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{employeeId}")
    public Employee updateEmployee(@Parameter(description = "Id of the Employee") @PathVariable @Min(value = 1) long employeeId,
                                   @Valid @RequestBody EmployeeRequest employeeRequest){
        Employee dbEmployee = employeeService.update(employeeId, employeeRequest);
        return dbEmployee;
    }

    @Operation(summary = "Delete the Employee", description = "removes the Employee from database using employeeId")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{employeeId}")
    public void deleteEmployee(@Parameter(description = "Id of the Employee") @PathVariable @Min(value = 1) long employeeId){
        employeeService.deleteById(employeeId);
    }

}
