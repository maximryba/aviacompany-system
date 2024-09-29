package komarov.avia.aviacompany.service;

import komarov.avia.aviacompany.entity.Airplane;
import komarov.avia.aviacompany.repository.AirplanesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirplanesServiceImpl implements AirplanesService {

    private final AirplanesRepository airplanesRepository;

    @Override
    public List<Airplane> getAllAirplanes() {
        return this.airplanesRepository.getAllAirplanes();
    }

    @Override
    public Optional<Airplane> getAirplaneById(int id) {
        return this.airplanesRepository.getAirplaneById(id);
    }

    @Override
    public Optional<Airplane> getAirplaneByName(String name) {
        return this.airplanesRepository.getAirplaneByName(name);
    }

    @Override
    public int addAirplane(Airplane airplane) {
        return this.airplanesRepository.saveAirplane(airplane);
    }

    @Override
    public void updateAirplane(Airplane airplane, int id) {
        this.airplanesRepository.updateAirplane(airplane, id);
    }

    @Override
    public void deleteAirplane(int id) {
        this.airplanesRepository.deleteAirplane(id);
    }
}
