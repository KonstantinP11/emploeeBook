package skypro.emploeeBook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.emploeeBook.dto.Employee;
import skypro.emploeeBook.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")

public class DepartmentController {
    public DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalaryInDepartment(@RequestParam int department) {
        return departmentService.maxSalaryInDepartment(department);
    }

    @GetMapping("/min-salary")
    public Employee minSalaryInDepartment(@RequestParam int department) {
        return departmentService.minSalaryInDepartment(department);
    }

    @GetMapping(value = "/all", params = "department")
    public Collection<Employee> findEmployeesInDepartment(@RequestParam int department) {
        return departmentService.findEmployeesInDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findAll() {
        return departmentService.findAll();
    }
}