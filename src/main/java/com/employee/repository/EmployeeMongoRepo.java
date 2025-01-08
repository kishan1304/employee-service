package com.employee.repository;

import com.employee.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMongoRepo extends MongoRepository<Employee, Integer> {
}
