package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FlightRepositoryImpl implements FlightRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Flight> flightRowMapper = (rs, rowNum) -> {
        Flight flight = new Flight();
        flight.setId(rs.getInt("id"));
        flight.setAirplaneId(rs.getInt("airplane_id"));
        flight.setDepartureAirport(rs.getInt("departure_airport"));
        flight.setArrivalAirport(rs.getInt("arrival_airport"));
        flight.setDepartureTime(rs.getTime("departure_time"));
        flight.setArrivalTime(rs.getTime("arrival_time"));
        flight.setCost(rs.getBigDecimal("cost"));
        flight.setDistance(rs.getInt("distance"));
        flight.setPassengerCount(rs.getInt("passenger_count"));
        flight.setFlightDuration((Period) rs.getObject("flight_duration"));
        return flight;
    };

    @Override
    public List<Flight> findAll() {
        String sql = "SELECT * FROM flights";
        return jdbcTemplate.query(sql, flightRowMapper);
    }

    @Override
    public Optional<Flight> findById(int id) {
        String sql = "SELECT * FROM flights WHERE id = ?";
        return jdbcTemplate.query(sql, flightRowMapper, id).stream().findFirst();
    }

    @Override
    public int save(Flight flight) {
        String sql = "INSERT INTO flights (airplane_id, departure_airport, arrival_airport, departure_time, arrival_time," +
                " cost, distance, passenger_count, flight_duration)" +
                " VALUES (airplane_id = ?, departure_airport = ?, arrival_airport = ?," +
                "departure_time = ?, arrival_time = ?, cost = ?, distance = ?, passenger_count = ?," +
                "flight_duration = ?)";
        return jdbcTemplate.update(sql, flight.getAirplaneId(), flight.getDepartureAirport(), flight.getArrivalAirport(),
                flight.getDepartureTime(), flight.getArrivalTime(), flight.getCost(), flight.getDistance(),
                flight.getPassengerCount(), flight.getFlightDuration());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM flights WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public int update(int id, Flight flight) {
        String sql = "UPDATE flights set airplane_id = ?, departure_airport = ?, arrival_airport = ?," +
                "departure_time = ?, arrival_time = ?, cost = ?, distance = ?, passenger_count = ?," +
                "flight_duration = ? WHERE id = ?";
        return jdbcTemplate.update(sql, flight.getAirplaneId(), flight.getDepartureAirport(), flight.getArrivalAirport(),
                flight.getDepartureTime(), flight.getArrivalTime(), flight.getCost(), flight.getDistance(),
                flight.getPassengerCount(), flight.getFlightDuration(), id);
    }

    @Override
    public List<Flight> findBySearch(String departureCity, String destinationCity, Date departureDate, int passengerCount) {
        String sql = "SELECT * FROM flights " +
                "JOIN airports as dep ON flights.departure_airport = dep.id " +
                "JOIN airports as arr ON flights.arrival_airport = arr.id" +
                " WHERE dep.city = ? AND arr.city = ? AND arrival_time = ? AND " +
                "passenger_count = ?";
        return jdbcTemplate.query(sql, flightRowMapper, departureCity, destinationCity, departureDate, passengerCount);
    }

}
