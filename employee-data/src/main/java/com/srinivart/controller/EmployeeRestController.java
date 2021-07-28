package com.srinivart.controller;

import com.srinivart.model.Employee;
import com.srinivart.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    private EmployeeServiceImpl employeeService;

    public EmployeeRestController() {
        this.employeeService = new EmployeeServiceImpl();  // creating object for implementation class
    }

    public EmployeeRestController(EmployeeServiceImpl employeeService)
    {
        this.employeeService = employeeService;
    }


    @GetMapping("/get")
    public List<Employee> findALL()
    {

       return employeeService.findAll();
    }

    @GetMapping("/find/{empId}")
    public Employee getEmployeeById(@PathVariable int empId)
    {
        Employee employee = employeeService.findById(empId);
//        if(employee==null)
//        {
//            throw new RuntimeException("Employee id not found " );
//        }
        return employee;
    }

    @PostMapping("/post")
    public Employee addEmployee(@RequestBody Employee employee)
    {
        System.out.println("post");
        employeeService.save(employee);
        return employee;
    }

    @PutMapping("update/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId,@RequestBody Employee employee)
    {
        Employee employee1 = employeeService.update(employeeId,employee);
        return employee1;
    }

    @DeleteMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId)
    {
        employeeService.deleteById(employeeId);
        return "Deleted employee with id: " + employeeId;
    }



}
