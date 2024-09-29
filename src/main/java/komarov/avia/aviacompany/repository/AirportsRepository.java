package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportsRepository {

    List<Airport> getAllAirports();

    Optional<Airport> getAirportByName(String name);

    List<Airport> getAirportsByCity(String city);

    Optional<Airport> getAirportById(int id);

    int addAirport(Airport airport);

    int updateAirport(Airport airport, int id);

    void deleteAirport(int id);
}
