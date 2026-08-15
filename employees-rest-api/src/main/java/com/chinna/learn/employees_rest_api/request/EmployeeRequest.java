package com.chinna.learn.employees_rest_api.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeRequest {

    @NotBlank(message = "First Name is Mandatory")
    @Size(min = 2, max = 50, message = "First Name must be between 2 and 50 in length")
    private String firstName;

    @NotBlank(message = "Last Name is Mandatory")
    @Size(min = 2, max = 50, message = "Last Name must be between 2 and 50 in length")
    private String lastName;

    @NotBlank(message = "Email is Mandatory")
    @Email(message = "Please provide valid email address")
    private String email;

    public EmployeeRequest(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
