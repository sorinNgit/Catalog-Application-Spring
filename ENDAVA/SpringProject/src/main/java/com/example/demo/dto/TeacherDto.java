package com.example.demo.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

public class TeacherDto extends TeacherBasicInfoDto{

    @NotNull(message = "employmentDate.cannot.be.null")
    private LocalDate employmentDate;

    @NotNull(message = "birthDate.cannot.be.null")
    private LocalDate birthDate;

    //@Size(min = 13, max = 13, message = "cnp.size.invalid")
    //@Pattern(regexp = "[0-9]+", message = "cnp.invalid")
    @Pattern(regexp = "[0-9]{13}", message = "cnp.invalid")
    private String cnp;

    @NotNull(message = "salary.cannot.be.null")
    @Min(value = 0, message = "salary.cannot.be.negative")
    private Long salary;

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }


}
