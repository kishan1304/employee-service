package com.employee.service;

import java.util.Optional;

import com.employee.entity.Employee;
import com.employee.exceptions.ResourceNotFoundException;
import com.employee.repository.EmployeeMongoRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeMongoRepo employeeMongoRepo;

    public Employee saveEmployee(Employee employee) {
        try {
            return employeeMongoRepo.save(employee);
        } catch (Exception e) {
            log.info("Error while saving employee with name : {}", employee);
            throw new RuntimeException("Error while saving employee, exception : "+e.getMessage());
        }
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {
        try {
            return employeeMongoRepo.findAll(pageable);
        } catch (Exception e) {
            log.info("Error while fetching all employees");
            throw new RuntimeException("Error while fetching all employees, exception : "+e.getMessage());
        }
    }

    public void deleteEmployee(int id) {
        try {
            employeeMongoRepo.deleteById(id);
        } catch (Exception e) {
            log.info("Error while deleting employee by id : {}", id);
            throw new RuntimeException("Error while deleting employee, exception : " +e.getMessage());
        }
    }

    public Optional<Employee> getEmployeeById(int id) {
        try {
            return employeeMongoRepo.findById(id);
        } catch (Exception e) {
            log.info("Error while fetching employee by id : {}", id);
            throw new RuntimeException("Error while fetching employee by id, exception : " +e.getMessage());
        }
    }

    public Employee updateEmployee(int id, Employee updatedEmployee) {
        Employee existingEmployee = employeeMongoRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));

        existingEmployee.setName(updatedEmployee.getName());
        existingEmployee.setDepartment(updatedEmployee.getDepartment());

        try {
            return employeeMongoRepo.save(existingEmployee);
        } catch (Exception e) {
            log.info("Error while updating employee to id : {}", id);
            throw new RuntimeException("Error while updating employee to id, exception : " +e.getMessage());
        }

    }
}
