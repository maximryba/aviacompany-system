package komarov.avia.aviacompany.service;

import komarov.avia.aviacompany.entity.Flight;
import komarov.avia.aviacompany.entity.FlightSearch;
import komarov.avia.aviacompany.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightsServiceImpl implements FlightsService {

    private final FlightRepository flightRepository;

    @Override
    public List<Flight> findAll() {
        return this.flightRepository.findAll();
    }

    @Override
    public List<Flight> findAllBySearch(FlightSearch search) {
        return this.flightRepository.findBySearch(search.getDepartureCity(), search.getDestinationCity(),
                search.getDepartureDate(), search.getPassengerCount());
    }


    @Override
    public Optional<Flight> findById(int id) throws NoSuchElementException {
        return Optional.ofNullable(this.flightRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    @Transactional
    @Override
    public int save(Flight flight) {
        try {
            return this.flightRepository.save(flight);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    @Transactional
    @Override
    public void delete(int id) {
        try {
            this.flightRepository.delete(id);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }

    @Transactional
    @Override
    public int update(int id, Flight flight) {
        try {
            return this.flightRepository.update(id, flight);
        } catch (Exception e) {
            throw new NoSuchElementException();
        }
    }
}
