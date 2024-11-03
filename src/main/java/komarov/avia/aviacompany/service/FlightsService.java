package komarov.avia.aviacompany.service;

import komarov.avia.aviacompany.entity.Flight;
import komarov.avia.aviacompany.entity.FlightSearch;

import java.util.List;
import java.util.Optional;

public interface FlightsService {
    List<Flight> findAll();

    Optional<Flight> findById(int id);

    int save(Flight flight);

    void delete(int id);

    int update(int id, Flight flight);

    List<Flight> findAllBySearch(FlightSearch flightSearch);
    
    List<String> findDepartureCity(String query);
    
    List<String> findArrivalCity(String query);
}
