package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Seat;
import komarov.avia.aviacompany.entity.SeatTypeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SeatsRepositoryImpl implements SeatsRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Seat> seatsRowMapper = (rs, rowNum) -> {
        Seat seat = new Seat();
        seat.setId(rs.getInt("id"));
        seat.setAirplaneId(rs.getInt("airplane_id"));
        seat.setType(rs.getInt("type"));
        seat.setNumber(rs.getInt("number"));
        return seat;
    };

    @Override
    public void addSeats(int capacity, int airplaneId) {
        String sql = "insert into seats (airplane_id, type, number) values (?, ?, ?)";
        int businessCapacity = (int) (capacity - (0.8 * capacity));
        for (int i = 1; i <= capacity - businessCapacity; i++) {
            this.jdbcTemplate.update(sql, airplaneId, 2, i);
        }
        for (int i = capacity - businessCapacity; i <= capacity; i++) {
            this.jdbcTemplate.update(sql, airplaneId, 1, i);
        }
    }
}
