package com.srinivart.service;

import com.srinivart.model.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(int id);

    public void save(Employee employee);

    public void deleteById(int id);

    public Employee update(int employeeId, Employee employee);
}
