package fttsr.dev.springbootempl.employees;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends
                 // тип сущности и тип id
        JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

}
