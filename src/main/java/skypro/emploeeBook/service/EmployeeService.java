package skypro.emploeeBook.service;

import skypro.emploeeBook.dto.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int department, double salary);

    Employee removeEmployee(String firstName, String lastName, int department, double salary);

    Employee findEmployee(String firstName, String lastName, int department, double salary);

    Collection<Employee> printAll();

    String generateKey(String firstName, String lastKey);
}