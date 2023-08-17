package com.shop_center.hyper_market_ali.employee;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);

    }

    @Transactional
    public void updateEmployee(Long employeeId, String firstName, String lastName, String email, String phoneNumber,
            String role, Double salary) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {
            Employee employee = optionalEmployee.get();

            if (firstName != null) {
                employee.setFirstName(firstName);
            }
            if (lastName != null) {
                employee.setLastName(lastName);
            }
            if (email != null) {
                employee.setEmail(email);
            }
            if (phoneNumber != null) {
                employee.setPhoneNumber(phoneNumber);
            }
            if (role != null) {
                employee.setPhoneNumber(phoneNumber);
            }

            employeeRepository.save(employee);
        } else {
            throw new EntityNotFoundException("Employee with ID " + employeeId + " not found");
        }
    }

    public void deleteEmployeeById(Long id) {
        boolean exists = employeeRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Employee with id " + id + " doesn't exist!");
        }

        employeeRepository.deleteById(id);
    }

    public Optional<Employee> findEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (!employeeOptional.isPresent()) {
            throw new IllegalStateException("Employee with id " + id + " doesn't exist!");
        }

        return employeeOptional;
    }

    public void addEmployee(Employee employee) {
        Optional<Employee> employeeOptional = employeeRepository.findEmployeeByEmail(employee.getEmail());
        if (employeeOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        employeeRepository.save(employee);
    }
}
