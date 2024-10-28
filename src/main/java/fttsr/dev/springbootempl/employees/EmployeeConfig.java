package fttsr.dev.springbootempl.employees;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class EmployeeConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            EmployeeRepository employeeRepository // Сохраним в репозиторий (БД)
    ) {
        return (args) -> {
            var employeeList = List.of(
                new Employee(
                        null,   // ID указывает сама БД
                        "Nick",
                        "fetteser.niox@gmail.com",
                        LocalDate.of(2005, 2, 10),
                        47000
                ),
                new Employee(
                        null,
                        "Egor",
                        "egor.niox@gmail.com",
                        LocalDate.of(1998, 4, 8),
                        74000
                )

            );
            employeeRepository.saveAll(employeeList); // Само сохранение в БД
        };
    }
}
