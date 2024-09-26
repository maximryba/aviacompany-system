package komarov.avia.aviacompany.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Employee {
    private int id;
    private String name;
    private String phone;
    private int salary;
    private Date hireDate;
    private Date modifiedDate;
}
