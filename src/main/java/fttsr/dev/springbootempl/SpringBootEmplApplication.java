package fttsr.dev.springbootempl;

import fttsr.dev.springbootempl.employees.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication  // Аннотация для пометки, что класс основной
public class SpringBootEmplApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEmplApplication.class, args);
    }

}
