package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository {
    List<Passenger> findAll();

    Optional<Passenger> findById(int id);

    Passenger save(Passenger passenger);

    void delete(int id);

    List<Passenger> findByUserId(int userId);

    void update(Passenger passenger, int id);
}
