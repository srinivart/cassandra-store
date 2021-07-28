package com.srinivart.service;

import com.srinivart.model.Employee;
import com.srinivart.util.Helper;

import java.io.IOException;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService{

    Helper helper = new Helper();

    @Override
    public List<Employee> findAll() {
        helper.readFromTable();
        return null;
    }

    @Override
    public Employee findById(int id){
        return helper.readFromTable1(id);

    }

    @Override
    public void save(Employee emp) {

        helper.insertIntoTable1(emp.getEmpid(),emp.getName(),emp.getDesignation(),emp.getSalary());
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public Employee update(int employeeId, Employee emp) {
        return helper.updateTable1(employeeId,emp);
    }
}
