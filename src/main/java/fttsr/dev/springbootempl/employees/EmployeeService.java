package fttsr.dev.springbootempl.employees;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    // inject репозитория в сервис
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getALlEmployees() {
        return employeeRepository.findAll();
    }

    public Employee createEmployee(Employee employee) {
        // Валидация:

        // :id != NULL
        if (employee.getId() != null) {
            throw new IllegalArgumentException("Id should be created by DB.");
        }
        // :unique email
        if (employeeRepository.findByEmail(employee.getEmail())
                .isPresent()) {
            throw new IllegalArgumentException("Email has been already taken");
        }
        // :salary > 13000
        if (employee.getSalary() <= 13000) {
            throw new IllegalArgumentException("Salary must be over 13k.");
        }

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        if (employeeRepository.findById(id).isEmpty()) {
            throw new IllegalArgumentException("Employee not found by id=%s"
                    .formatted(id));
        }
        employeeRepository.deleteById(id);
    }

    @Transactional  // Транзакционный метод - JPA-сущности пробрасываются в БД
    public void updateEmployee(
            Long id,
            String email,
            Integer salary
    ) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Employee not found by id = %s"
                        .formatted(id))
                );
        if (email != null
            && !email.isEmpty()
            && !email.equals(employee.getEmail())) {
            Optional<Employee> employeeOpt = employeeRepository.findByEmail(email);
            if (employeeOpt.isPresent()) {
                throw new IllegalArgumentException("Email has been already taken");
            }
            employee.setEmail(email);
        }

        if (salary != null) {
            if (salary < 13000) {
                throw new IllegalArgumentException("Salary should be over 13k.");
            }
            employee.setSalary(salary);
        }
    }
}
