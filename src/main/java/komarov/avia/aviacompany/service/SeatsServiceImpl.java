package komarov.avia.aviacompany.service;

import komarov.avia.aviacompany.repository.SeatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SeatsServiceImpl implements SeatsService {

    private final SeatsRepository seatsRepository;

    @Transactional
    @Override
    public void addSeats(int capacity, int airplaneId) {
        this.seatsRepository.addSeats(capacity, airplaneId);
    }
}
