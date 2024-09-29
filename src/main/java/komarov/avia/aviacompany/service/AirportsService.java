package komarov.avia.aviacompany.service;

import komarov.avia.aviacompany.entity.Airport;

import java.util.List;
import java.util.Optional;

public interface AirportsService {

    List<Airport> getAllAirports();

    Optional<Airport> getAirportById(int id);

    Optional<Airport> getAirportByName(String name);

    List<Airport> getAirportsByCity(String city);

    int addAirport(Airport airport);

    int updateAirport(Airport airport, int id);

    void deleteAirport(int id);
}
