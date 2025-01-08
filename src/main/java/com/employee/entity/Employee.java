package com.employee.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee")
public class Employee {

    @Id
    private Long id;
    private String name;
    private String department;
    private double salary;

}