package skypro.emploeeBook.service;

import org.springframework.stereotype.Service;
import skypro.emploeeBook.dto.Employee;
import skypro.emploeeBook.exceptions.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StreamServiceImpl implements StreamService {
    private EmployeeService employeeService;

    public StreamServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalaryInDepartment(int department) {
        return employeeService.printAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public Employee minSalaryInDepartment(int department) {
        return employeeService.printAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(employee -> employee.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public Collection<Employee> findEmployeesInDepartment(int department) {
        return employeeService.printAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> findAll() {
        return employeeService.printAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment()));
    }
}