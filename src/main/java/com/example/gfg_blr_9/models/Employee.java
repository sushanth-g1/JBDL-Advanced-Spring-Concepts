package com.example.gfg_blr_9.models;

import com.example.gfg_blr_9.annotations.InitSalary;
import com.example.gfg_blr_9.annotations.JsonSerializableField;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Employee {
    private int id;
    @JsonSerializableField("Mr")
    private String firstName;
    @JsonSerializableField
    private String lastName;
   @JsonSerializableField("age")
    private int age;
    private int salary;

    public Employee(int id, String firstName, String lastName, int age, int salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age =age;
        this.id = id;
        this.salary = salary;
    }


    public String getEmployeeDetails(String title){
        return title + " "+ firstName + " "+lastName;
    }
    @InitSalary(10000)
    public void initialiseSalary(int salary){
        this.salary = salary;
    }

    public Employee constructEmployee(int id, String firstName, String lastName, int age, int salary){
        return new Employee(id, firstName, lastName, age, salary);
    }
}
