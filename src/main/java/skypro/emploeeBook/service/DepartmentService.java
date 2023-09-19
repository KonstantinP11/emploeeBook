package skypro.emploeeBook.service;

import skypro.emploeeBook.dto.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Employee maxSalaryInDepartment(int department);

    Employee minSalaryInDepartment(int department);

    Collection<Employee> findEmployeesInDepartment(int department);

    Map<Integer, List<Employee>> findAll();
}