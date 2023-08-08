package skypro.emploeeBook.service;

import org.springframework.stereotype.Service;
import skypro.emploeeBook.dto.Employee;
import skypro.emploeeBook.exceptions.EmployeeAlreadyAddedException;
import skypro.emploeeBook.exceptions.EmployeeNotFoundException;
import skypro.emploeeBook.exceptions.EmployeeStorageIsFullException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private List<Employee> employees;
    private static final int EMPLOYEES_SIZE = 3;

    public EmployeeServiceImpl() {
        this.employees = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (employees.size() < EMPLOYEES_SIZE) {
            Employee employee = new Employee(firstName, lastName);
            if (employees.contains(employee)) {
                throw new EmployeeAlreadyAddedException();
            }
            employees.add(employee);
            return employee;
        } else {
            throw new EmployeeStorageIsFullException();
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.remove(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }
  @Override
    public Collection<Employee> printAll() {
        return employees;
    }
}