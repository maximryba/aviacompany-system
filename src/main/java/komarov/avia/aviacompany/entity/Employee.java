package komarov.avia.aviacompany.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class Employee {
    private int id;
    private String name;
    private String phone;
    private int salary;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hireDate;
    private Date modifiedDate;
}
