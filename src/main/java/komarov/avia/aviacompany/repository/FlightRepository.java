package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Flight;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface FlightRepository {
    List<Flight> findAll();

    Optional<Flight> findById(int id);

    int save(Flight flight);

    void delete(int id);

    int update(int id, Flight flight);

    List<Flight> findBySearch(String departureCity, String destinationCity, Date departureDate, int passengerCount);

	List<String> findArrivalCities(String query);

	List<String> findDepartureCities(String query);
}
