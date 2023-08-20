package skypro.emploeeBook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.emploeeBook.dto.Employee;
import skypro.emploeeBook.service.StreamService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")

public class StreamController {
    public StreamService streamService;

    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalaryInDepartment(@RequestParam int department) {
        return streamService.maxSalaryInDepartment(department);
    }

    @GetMapping("/min-salary")
    public Employee minSalaryInDepartment(@RequestParam int department) {
        return streamService.minSalaryInDepartment(department);
    }

    @GetMapping(value = "/all", params = "department")
    public Collection<Employee> findEmployeesInDepartment(@RequestParam int department) {
        return streamService.findEmployeesInDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findAll() {
        return streamService.findAll();
    }
}