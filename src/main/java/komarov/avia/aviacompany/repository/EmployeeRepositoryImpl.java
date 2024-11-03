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
        employee.setPosition(rs.getString("position"));
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
        String sql = "insert into employee (name, phone, position, salary, hire_date, modified_date) values (?, ?, ?, ?, ?, ?)";

        LocalDate localDate = employee.getHireDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        employee.setHireDate(date);

        return jdbcTemplate.update(sql,
                employee.getName(),
                employee.getPhone(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getHireDate(),
                new Timestamp(System.currentTimeMillis()));
    }

    @Override
    public void update(Employee employee, int id) {
        String sql = "update employee set name = ?, phone = ?, position = ?, salary = ?, hire_date = ?, modified_date = NOW() where id = ?";

        LocalDate localDate = employee.getHireDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        employee.setHireDate(date);

        this.jdbcTemplate.update(sql,
                employee.getName(),
                employee.getPhone(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getHireDate(),
                id);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from employee where id = ?";
        this.jdbcTemplate.update(sql, id);
    }
    
    @Override
    public void assign(Employee employee, int id) {
    	String firstSql = "select flight_id from flights_employee where employee_id = ?";
    	Optional<Employee> empl_id = this.jdbcTemplate.query(firstSql, this.employeetRowMapper, employee.getId()).stream().findFirst();
    	if (empl_id.isPresent()) {
    		String sql = "update flights_employee set flight_id = ? where employee_id = ?";
        	this.jdbcTemplate.update(sql, id, employee.getId());
    	} else {
    		String sql = "insert into flights_employee (flight_id, employee_id) values (?, ?)";
        	this.jdbcTemplate.update(sql, employee.getId(), id);
    	}
    }
}
