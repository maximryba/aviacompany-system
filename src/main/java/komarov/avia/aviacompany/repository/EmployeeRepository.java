package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    List<Employee> findAll();

    Optional<Employee> findById(int id);

    Optional<Employee> findByName(String name);

    int save(Employee employee);

    void update(Employee employee, int id);

    void delete(int id);

}