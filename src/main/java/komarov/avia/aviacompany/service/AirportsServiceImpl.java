package komarov.avia.aviacompany.service;

import komarov.avia.aviacompany.entity.Airport;
import komarov.avia.aviacompany.repository.AirportsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportsServiceImpl implements AirportsService {
    private final AirportsRepository airportsRepository;

    @Override
    public List<Airport> getAllAirports() {
        return this.airportsRepository.getAllAirports();
    }

    @Override
    public Optional<Airport> getAirportById(int id) {
        return this.airportsRepository.getAirportById(id);
    }

    @Override
    public Optional<Airport> getAirportByName(String name) {
        return this.airportsRepository.getAirportByName(name);
    }

    @Override
    public List<Airport> getAirportsByCity(String city) {
        return this.airportsRepository.getAirportsByCity(city);
    }

    @Transactional
    @Override
    public int addAirport(Airport airport) {
        return this.airportsRepository.addAirport(airport);
    }

    @Transactional
    @Override
    public int updateAirport(Airport airport, int id) {
        return this.airportsRepository.updateAirport(airport, id);

    }

    @Transactional
    @Override
    public void deleteAirport(int id) {
        this.airportsRepository.deleteAirport(id);
    }
}
