package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Employee> employeetRowMapper = (rs, rowNum) -> {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setPhone(rs.getString("phone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setModifiedDate(rs.getDate("modified_date"));
        return employee;
    };

    @Override
    public List<Employee> findAll() {
        String sql = "select * from employee";

        return jdbcTemplate.query(sql, employeetRowMapper);
    }

    @Override
    public Optional<Employee> findById(int id) {
        String sql = "select * from employee where id = ?";

        return jdbcTemplate.query(sql, employeetRowMapper, id).stream().findFirst();
    }

    @Override
    public Optional<Employee> findByName(String name) {
        String sql = "select * from employee where name = ?";

        return jdbcTemplate.query(sql, employeetRowMapper, name).stream().findFirst();
    }

    @Override
    public int save(Employee employee) {
        String sql = "insert into employee (name, phone, salary, hire_date, modified_date) values (?, ?, ?, ?, ?)";

        LocalDate localDate = employee.getHireDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        employee.setHireDate(date);

        return jdbcTemplate.update(sql,
                employee.getName(),
                employee.getPhone(),
                employee.getSalary(),
                employee.getHireDate(),
                new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public void update(Employee employee, int id) {
        String sql = "update employee set name = ?, phone = ?, salary = ?, hire_date = ?, modified_date = NOW() where id = ?";

        LocalDate localDate = employee.getHireDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        employee.setHireDate(date);

        this.jdbcTemplate.update(sql,
                employee.getName(),
                employee.getPhone(),
                employee.getSalary(),
                employee.getHireDate(),
                new Timestamp(System.currentTimeMillis()),
                id);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from employee where id = ?";
        this.jdbcTemplate.update(sql, id);
    }
}
