package fttsr.dev.springbootempl.employees;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// Контроллер для обработки http запросов

@RestController
public class EmployeeController {

    // Перенаправляем запрос в сервис слой
    private final EmployeeService employeeService;

    @Autowired // DI
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping // Пометка, что метод является обработчиком GET-запросов
    public List<Employee> helloWorld() {
        return employeeService.getALlEmployees();
    }

    @PostMapping
    public Employee createEmployee(
            @RequestBody Employee employee
    ) {
        return employeeService.createEmployee(employee);
    }

    // delete localhost:8080/4
    @DeleteMapping("/{employeeId}")
    public void deleteEmployee(
            @PathVariable("employeeId") Long id
    ) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/{employeeId}")
    public void updateEmployee(
            @PathVariable("employeeId") Long id,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "salary", required = false) Integer salary
    ) {
        employeeService.updateEmployee(id, email, salary );
    }
}
