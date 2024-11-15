package komarov.avia.aviacompany.service;

import komarov.avia.aviacompany.entity.Passenger;
import komarov.avia.aviacompany.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    public List<Passenger> findAll() {
        return this.passengerRepository.findAll();
    }

    @Override
    public Optional<Passenger> findById(int id) {
        return this.passengerRepository.findById(id);
    }

    @Transactional
    @Override
    public Passenger save(Passenger passenger) {
        return this.passengerRepository.save(passenger);
    }

    @Transactional
    @Override
    public void delete(int id) {
        this.passengerRepository.delete(id);
    }

    @Override
    public List<Passenger> findByUserId(int userId) {
        return this.passengerRepository.findByUserId(userId);
    }

    @Transactional
    @Override
    public void update(Passenger passenger, int id) {
        this.passengerRepository.update(passenger, id);
    }
}