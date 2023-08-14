package com.shop_center.hyper_market_ali.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop_center.hyper_market_ali.customer.Customer;
import com.shop_center.hyper_market_ali.customer.CustomerService;

@RestController
@RequestMapping("api/v1/employees")
public class EmployeeController {

    @Autowired
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    @PostMapping
    public void addEmployee(@RequestBody Employee request) {
        Employee employee = new Employee();
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setRole(request.getRole());
        employee.setSalary(request.getSalary());
        employee.setAddress(request.getAddress());
        employeeService.addEmployee(employee);
    }

    @DeleteMapping("{employeeId}")
    public void deleteEmployee(@PathVariable("employeeId") Long id) {
        employeeService.deleteEmployeeById(id);
    }

    @PutMapping("{employeeId}")
    public void updateEmployee(@PathVariable("employeeId") Long id, @RequestBody Employee request) {
        employeeService.updateEmployee(id, request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getPhoneNumber(), request.getRole(), request.getSalary());
    }
}
