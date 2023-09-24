package skypro.emploeeBook.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import skypro.emploeeBook.dto.Employee;
import skypro.emploeeBook.exceptions.EmployeeAlreadyAddedException;
import skypro.emploeeBook.exceptions.EmployeeNotFoundException;
import skypro.emploeeBook.exceptions.EmployeeStorageIsFullException;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl underTest = new EmployeeServiceImpl();
    private final Employee employeeTest = new Employee(
            "Ivan", "Ivanow", 2, 50_000);

    @Test
    void addEmployee_ShouldAddEmployeeAndReturnEmployee() {
        Employee result = underTest.addEmployee(employeeTest.getFirthName(), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary());
        assertTrue(underTest.printAll().contains(employeeTest));
        assertEquals(employeeTest, result);
    }

    @Test
    void addEmployee_ShouldThrowExceptionWhenNotEnoughMapSize() {
        underTest.addEmployee("Petr", "Petrov", 2, 100_000);
        underTest.addEmployee("Oleg", "Olegov", 3, 50_000);
        underTest.addEmployee("Ilya", "Ilin", 3, 150_000);
        assertThrows(EmployeeStorageIsFullException.class, () -> underTest.addEmployee(
                employeeTest.getFirthName(), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary()));
    }

    @Test
    void addEmployee_ShouldThrowWhenEqualEmployeeIsInMap() {
        underTest.addEmployee(employeeTest.getFirthName(), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary());
        assertThrows(EmployeeAlreadyAddedException.class, () -> underTest.addEmployee(
                employeeTest.getFirthName(), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary()));

    }

    @Test
    void removeEmployee_ShouldRemoveEmployeeFromMap() {
        underTest.addEmployee(employeeTest.getFirthName(), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary());
        underTest.removeEmployee(employeeTest.getFirthName(), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary());
        assertFalse(underTest.printAll().contains(employeeTest));
    }

    @Test
    void removeEmployee_ShouldThrowWhenEmployeeNotInMap() {
        assertThrows(EmployeeNotFoundException.class, () -> underTest.removeEmployee(employeeTest.getFirthName(), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary()));
    }

    @Test
    void findEmployee_ShouldReturnEmployee() {
        underTest.addEmployee(employeeTest.getFirthName(), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary());
        underTest.findEmployee(employeeTest.getFirthName(), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary());
        assertTrue(underTest.printAll().contains(employeeTest));
    }

    @Test
    void findEmployee_ShouldThrowEmployeeNotFoundException() {
        underTest.addEmployee(employeeTest.getFirthName(), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary());
        assertThrows(EmployeeNotFoundException.class, () -> underTest.findEmployee(
                (employeeTest.getFirthName() + "No"), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary()));
    }

    @Test
    void printAll_ShouldReturnListEmployee() {
        Employee employee = new Employee("Petr", "Petrov",
                2, 100_000);
        underTest.addEmployee(employeeTest.getFirthName(), employeeTest.getLastName(),
                employeeTest.getDepartment(), employeeTest.getSalary());
        underTest.addEmployee(employee.getFirthName(), employee.getLastName(),
                employee.getDepartment(), employee.getSalary());
        Collection<Employee> result = underTest.printAll();
        assertTrue(result.containsAll(List.of(employeeTest,employee)));
    }

    @Test
    void printAll_ShouldReturnEmptyListWhenEmployeeNotInMap() {
        Collection<Employee> result = underTest.printAll();
        assertTrue(result.isEmpty());
    }
}