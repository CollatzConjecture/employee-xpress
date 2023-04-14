package com.employee.express.crudEmployee.controller;

import com.employee.express.crudEmployee.entity.Employee;
import com.employee.express.crudEmployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    private EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/")
    public RedirectView redirectToEmployeeList() {
        return new RedirectView("/employees/list");
    }

    @GetMapping("")
    public RedirectView redirectToEmployeeList2() {
        return new RedirectView("/employees/list");
    }

    @GetMapping("/list")
    public String listEmployees(Model theModel) {

        List<Employee> theEmployees = employeeService.findAll();

        theModel.addAttribute("employees", theEmployees);

        return "/employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId,
                                    Model theModel) {

        Employee theEmployee = employeeService.findById(theId);

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        employeeService.save(theEmployee);

        return "redirect:/employees/list";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        employeeService.deleteById(theId);

        return "redirect:/employees/list";
    }
}
