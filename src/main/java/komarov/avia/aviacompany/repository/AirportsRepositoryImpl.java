package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Airport;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AirportsRepositoryImpl implements AirportsRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Airport> airportRowMapper = (rs, rowNum) -> {
        Airport airport = new Airport();
        airport.setId(rs.getInt("id"));
        airport.setCity(rs.getString("city"));
        airport.setName(rs.getString("name"));
        airport.setGoodsService(rs.getBoolean("goods_service"));
        airport.setCountry(rs.getString("country"));
        return airport;
    };

    @Override
    public List<Airport> getAllAirports() {
        String sql = "select * from airports";
        return jdbcTemplate.query(sql, airportRowMapper);
    }

    @Override
    public Optional<Airport> getAirportByName(String name) {
        String sql = "select * from airports where name = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, airportRowMapper, name));
    }

    @Override
    public List<Airport> getAirportsByCity(String city) {
        String sql = "select * from airports where city = ?";
        return jdbcTemplate.query(sql, airportRowMapper, city);
    }

    @Override
    public Optional<Airport> getAirportById(int id) {
        String sql = "select * from airports where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, airportRowMapper, id));
    }

    @Override
    public int addAirport(Airport airport) {
        String sql = "insert into airports(city, name, goods_service, country) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, airport.getCity(), airport.getName(), airport.isGoodsService(), airport.getCountry());
    }

    @Override
    public int updateAirport(Airport airport, int id) {
        String sql = "update airports set name = ?, city = ?, goods_service = ?, country = ? where id = ?";
        return jdbcTemplate.update(sql, airport.getName(), airport.getCity(), airport.isGoodsService(), airport.getCountry(),
                id);
    }

    @Override
    public void deleteAirport(int id) {
        String sql = "delete from airports where id = ?";
        jdbcTemplate.update(sql, id);
    }
}
