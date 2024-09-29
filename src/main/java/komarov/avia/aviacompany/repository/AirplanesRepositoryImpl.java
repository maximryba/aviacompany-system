package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Airplane;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AirplanesRepositoryImpl implements AirplanesRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Airplane> airplaneRowMapper = (rs, rowNum) -> {
        Airplane airplane = new Airplane();
        airplane.setId(rs.getInt("id"));
        airplane.setName(rs.getString("name"));
        airplane.setFuelCapacity(rs.getInt("fuel_capacity"));
        airplane.setPassengerCapacity(rs.getInt("passenger_capacity"));
        airplane.setServiceCost(rs.getInt("service_cost"));
        return airplane;
    };

    @Override
    public List<Airplane> getAllAirplanes() {
        String sql = "select * from airplanes";

        return jdbcTemplate.query(sql, airplaneRowMapper);
    }

    @Override
    public Optional<Airplane> getAirplaneById(int id) {
        String sql = "select * from airplanes where id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, airplaneRowMapper, id));
    }

    @Override
    public Optional<Airplane> getAirplaneByName(String name) {
        String sql = "select * from airplanes where name = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, airplaneRowMapper, name));
    }

    @Transactional
    @Override
    public int saveAirplane(Airplane airplane) {
        String sql = "insert into airplanes(name, fuel_capacity, passenger_capacity, service_cost) values(?,?,?,?)";
        return jdbcTemplate.update(sql, airplane.getName(), airplane.getFuelCapacity(), airplane.getPassengerCapacity(),
                airplane.getServiceCost());
    }

    @Transactional
    @Override
    public void updateAirplane(Airplane airplane, int id) {
        String sql = "update airplanes set name = ?, fuel_capacity = ?, passenger_capacity = ?, service_cost = ?" +
                " where id = ?";
        jdbcTemplate.update(sql, airplane.getName(), airplane.getFuelCapacity(), airplane.getPassengerCapacity(),
                airplane.getServiceCost(), id);
    }

    @Transactional
    @Override
    public void deleteAirplane(int id) {
        String sql = "delete from airplanes where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
