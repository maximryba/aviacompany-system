package komarov.avia.aviacompany.repository;

import komarov.avia.aviacompany.entity.Airplane;

import java.util.List;
import java.util.Optional;

public interface AirplanesRepository {
    List<Airplane> getAllAirplanes();

    Optional<Airplane> getAirplaneById(int id);

    Optional<Airplane> getAirplaneByName(String name);

    int saveAirplane(Airplane airplane);

    void updateAirplane(Airplane airplane, int id);

    void deleteAirplane(int id);
}
