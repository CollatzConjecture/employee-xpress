package com.employee.express.crudEmployee.controller;

import com.employee.express.crudEmployee.entity.Employee;
import com.employee.express.crudEmployee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeRestController {

    private EmployeeService employeeService;

    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);

        if(theEmployee == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }

        return theEmployee;
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestParam Employee theEmployee) {

        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @PutMapping("update")
    public Employee updateEmployee(@RequestParam Employee theEmployee) {

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    @DeleteMapping("/delete/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {

        Employee temptEmployee = employeeService.findById(employeeId);

        if(temptEmployee == null) {
            throw new RuntimeException("Employee id not found - " + temptEmployee);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee id - " + employeeId;
    }

}
