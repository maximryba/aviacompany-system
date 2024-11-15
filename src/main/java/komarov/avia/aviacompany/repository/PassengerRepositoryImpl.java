package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Passenger;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PassengerRepositoryImpl implements PassengerRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Passenger> passengerRowMapper = (rs, rowNum) -> {
        Passenger passenger = new Passenger();
        passenger.setId(rs.getInt("id"));
        passenger.setFirstName(rs.getString("first_name"));
        passenger.setLastName(rs.getString("last_name"));
        passenger.setEmail(rs.getString("email"));
        passenger.setUserId(rs.getInt("user_id"));
        passenger.setPassport(rs.getString("passport"));
        passenger.setPhone(rs.getString("phone"));
        return passenger;
    };

    @Override
    public List<Passenger> findAll() {
        String sql = "select * from passengers";
        return this.jdbcTemplate.query(sql, passengerRowMapper);
    }

    @Override
    public Optional<Passenger> findById(int id) {
        String sql = "select * from passengers where id = ?";
        return this.jdbcTemplate.query(sql, passengerRowMapper, id).stream().findFirst();
    }

    @Override
    public Passenger save(Passenger passenger) {
        String sql = "insert into passengers (first_name, last_name, user_id, email, phone, passport) values (?, ?, ?, ?, ?, ?)";
        int newId = this.jdbcTemplate.update(sql,
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getUserId(),
                passenger.getEmail(),
                passenger.getPhone(),
                passenger.getPassport());
        return this.jdbcTemplate.queryForObject("select * from passengers where id = ?", passengerRowMapper, newId);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from passengers where id = ?";
        this.jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Passenger> findByUserId(int userId) {
        String sql = "select * from passengers where user_id = ?";
        return this.jdbcTemplate.query(sql, passengerRowMapper, userId);
    }

    @Override
    public void update(Passenger passenger, int id) {
        String sql = "update passengers set first_name = ?, last_name = ?, email = ?, phone = ?, passport = ? where id = ?";
        this.jdbcTemplate.update(sql, passenger.getFirstName(), passenger.getLastName(), passenger.getEmail(),
                passenger.getPhone(), passenger.getPassport(), id);
    }
}
