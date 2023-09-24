package skypro.emploeeBook.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.emploeeBook.dto.Employee;
import skypro.emploeeBook.exceptions.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    DepartmentServiceImpl departmentService;
    private List<Employee> employees = List.of(
            new Employee("Ivan", "Ivanow", 2, 50_000),
            new Employee("Petr", "Petrov", 2, 100_000),
            new Employee("Oleg", "Olegov", 3, 50_000));

    @Test
    void maxSalaryInDepartment_shouldReturnEmployeeWithMaxSalaryWhenEmployeeInDepartment() {
        when(employeeService.printAll()).thenReturn(employees);
        Employee result = departmentService.maxSalaryInDepartment(employees.get(0).getDepartment());

        assertEquals(employees.get(1), result);
    }

    @Test
    void maxSalaryInDepartment_shouldTrowEmployeeNotFoundExceptionWhenEmployeeNoInDepartment() {
        when(employeeService.printAll()).thenReturn(Collections.emptyList());
        assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.maxSalaryInDepartment(1));
    }

    @Test
    void minSalaryInDepartment_shouldReturnEmployeeWithMinSalaryWhenEmployeeInDepartment() {
        when(employeeService.printAll()).thenReturn(employees);
        Employee result = departmentService.minSalaryInDepartment(employees.get(0).getDepartment());
        assertEquals(employees.get(0), result);
    }

    @Test
    void minSalaryInDepartment_shouldTrowEmployeeNotFoundExceptionWhenEmployeeNoInDepartment() {
        when(employeeService.printAll()).thenReturn(Collections.emptyList());
        assertThrows(EmployeeNotFoundException.class,
                () -> departmentService.maxSalaryInDepartment(1));
    }

    @Test
    void findEmployeesInDepartment_shouldReturnEmployeesInDepartment() {
        when(employeeService.printAll()).thenReturn(employees);
        Collection<Employee> result = departmentService.findEmployeesInDepartment(
                employees.get(0).getDepartment());
        assertEquals(List.of(employees.get(0), employees.get(1)), result);
    }

    @Test
    void findEmployeesInDepartment_shouldReturnEmptyList() {
        when(employeeService.printAll()).thenReturn(Collections.emptyList());
        Collection<Employee> result = departmentService.findEmployeesInDepartment(1);
        assertEquals(List.of(), result);
    }

    @Test
    void findAll_shouldReturnMapEmployeesWhenEmployeeInDepartment() {
        when(employeeService.printAll()).thenReturn(employees);
        Map<Integer, List<Employee>> expectedMap = Map.of(
                employees.get(0).getDepartment(), List.of(employees.get(0), employees.get(1)),
                employees.get(2).getDepartment(), List.of(employees.get(2)));
        Map<Integer, List<Employee>> result = departmentService.findAll();
        assertEquals(expectedMap, result);
    }

    @Test
    void findAll_shouldReturnEmptyMap() {
        when(employeeService.printAll()).thenReturn(Collections.emptyList());
        Map<Integer, List<Employee>> expectedMap = Map.of();
        Map<Integer, List<Employee>> result = departmentService.findAll();
        assertEquals(expectedMap, result);
    }
}