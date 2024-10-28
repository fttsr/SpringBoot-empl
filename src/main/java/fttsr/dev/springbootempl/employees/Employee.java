package fttsr.dev.springbootempl.employees;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.Period;

@Entity // Сущность, которая может сохраняться в БД
@Table(name = "employee")
public class Employee {

    @Id
    @SequenceGenerator( // Как id генерируется
            name = "employee_sequence",
            sequenceName = "employee_sequence",
            allocationSize = 1
    )
    @GeneratedValue(    // Стратегия, как БД генерирует id
            strategy = GenerationType.SEQUENCE,
            generator = "employee_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private Integer salary;

    @Transient  // НЕ создаётся отдельная колонка Age в БД
    private Integer age;

    public Employee() {
    }

    public Employee(Long id, String name, String email, LocalDate birthDate, Integer salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.salary = salary;
        this.age = Period.between(
                birthDate,
                LocalDate.now()
        ).getYears();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getSalary() {
        return salary;
    }

    public Integer getAge() {
        if (age == null) {
            this.age = Period.between(
                    birthDate,
                    LocalDate.now()
            ).getYears();
        }
        return age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}

