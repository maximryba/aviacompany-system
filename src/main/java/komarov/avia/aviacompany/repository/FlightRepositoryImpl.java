package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        flight.setDepartureTime(rs.getTime("departure_time").toLocalTime());
        flight.setArrivalTime(rs.getTime("arrival_time").toLocalTime());
        flight.setCost(rs.getBigDecimal("cost"));
        flight.setDistance(rs.getInt("distance"));
        flight.setPassengerCount(rs.getInt("passenger_count"));
        flight.setFlightDuration((rs.getString("flight_duration")));
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
        String sql = "INSERT INTO flights (airplane_id, departure_airport, arrival_airport, departure_time, arrival_time, " +
                "cost, distance, passenger_count, flight_duration) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?::INTERVAL)";

        String flightDurationInterval = calculateFlightDurationAsInterval(
                flight.getDepartureTime(), flight.getArrivalTime());

        // Преобразуем LocalTime в LocalDateTime
        LocalDateTime departureDateTime = LocalDateTime.of(LocalDate.now(), flight.getDepartureTime());
        LocalDateTime arrivalDateTime = LocalDateTime.of(LocalDate.now(), flight.getArrivalTime());

        flight.setFlightDuration(flightDurationInterval);
        System.out.println(flight);
        return jdbcTemplate.update(sql,
                flight.getAirplaneId(),
                flight.getDepartureAirport(),
                flight.getArrivalAirport(),
                java.sql.Timestamp.valueOf(departureDateTime),  // Преобразование LocalTime в Time
                java.sql.Timestamp.valueOf(arrivalDateTime),    // Преобразование LocalTime в Time
                flight.getCost(),
                flight.getDistance(),
                flight.getPassengerCount(),
                flightDurationInterval  // Использование рассчитанной продолжительности полета
        );
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
                "flight_duration = ?::INTERVAL WHERE id = ?";

        String flightDurationInterval = calculateFlightDurationAsInterval(
                flight.getDepartureTime(), flight.getArrivalTime());

        // Преобразуем LocalTime в LocalDateTime
        LocalDateTime departureDateTime = LocalDateTime.of(LocalDate.now(), flight.getDepartureTime());
        LocalDateTime arrivalDateTime = LocalDateTime.of(LocalDate.now(), flight.getArrivalTime());

        return jdbcTemplate.update(sql, flight.getAirplaneId(), flight.getDepartureAirport(), flight.getArrivalAirport(),
                java.sql.Timestamp.valueOf(departureDateTime), java.sql.Timestamp.valueOf(arrivalDateTime), flight.getCost(), flight.getDistance(),
                flight.getPassengerCount(), flightDurationInterval, id);
    }

    @Override
    public List<Flight> findBySearch(String departureCity, String destinationCity, int passengerCount) {
        String sql = "SELECT * FROM flights " +
                "JOIN airports as dep ON flights.departure_airport = dep.id " +
                "JOIN airports as arr ON flights.arrival_airport = arr.id" +
                " WHERE dep.city = ? AND arr.city = ?";
        return jdbcTemplate.query(sql, flightRowMapper, departureCity, destinationCity);
    }

    public static String calculateFlightDurationAsInterval(LocalTime departureTime, LocalTime arrivalTime) {
        // Рассчитываем продолжительность между временем отправления и прибытия
        Duration duration = Duration.between(departureTime, arrivalTime);

        // Преобразуем продолжительность в минуты и часы
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;

        // Формируем строку в формате SQL INTERVAL
        return String.format("'%d hours %d minutes'", hours, minutes);
    }

    @Override
    public List<String> findDepartureCities(String query) {
        String sql = "SELECT DISTINCT a.city " +
                     "FROM flights f " +
                     "JOIN airports a ON f.departure_airport = a.id " +
                     "WHERE LOWER(a.city) LIKE LOWER(?)";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("city"), query);
    }

    @Override
    public List<String> findArrivalCities(String query) {
        String sql = "SELECT DISTINCT a.city " +
                     "FROM flights f " +
                     "JOIN airports a ON f.arrival_airport = a.id " +
                     "WHERE LOWER(a.city) LIKE LOWER(?)";
        return this.jdbcTemplate.query(sql, (rs, rowNum) -> rs.getString("city"), query);
    }


}
