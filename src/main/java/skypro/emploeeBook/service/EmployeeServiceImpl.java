package skypro.emploeeBook.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import skypro.emploeeBook.dto.Employee;
import skypro.emploeeBook.exceptions.EmploeeyBadRequestException;
import skypro.emploeeBook.exceptions.EmployeeAlreadyAddedException;
import skypro.emploeeBook.exceptions.EmployeeNotFoundException;
import skypro.emploeeBook.exceptions.EmployeeStorageIsFullException;

import java.util.*;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    private final Map<String, Employee> employees;
    private static final int EMPLOYEES_SIZE = 3;


    @Override
    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        checkInput(firstName, lastName);
        if (employees.size() < EMPLOYEES_SIZE) {
            Employee employee = new Employee(firstName, lastName, department, salary);
            String employeeKey = generateKey(firstName, lastName);
            if (employees.containsKey(employeeKey)) {
                throw new EmployeeAlreadyAddedException();
            }
            employees.put(employeeKey, employee);
            return employee;
        } else {
            throw new EmployeeStorageIsFullException();
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName, int department, double salary) {
        checkInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, department, salary);
        String employeeKey = generateKey(firstName, lastName);
        if (!employees.remove(employeeKey, employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName, int department, double salary) {
        checkInput(firstName, lastName);
        Employee employee = new Employee(firstName, lastName, department, salary);
        String employeeKey = generateKey(firstName, lastName);
        if (!employees.containsKey(employeeKey)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> printAll() {
        return employees.values();
    }

    private String generateKey(String firstName, String lastKey) {
        return firstName + lastKey;
    }

    private void checkInput(String firstName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(lastName))) {
            throw new EmploeeyBadRequestException();
        }
    }
}