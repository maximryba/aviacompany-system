package komarov.avia.aviacompany.service;

import komarov.avia.aviacompany.entity.Airplane;

import java.util.List;
import java.util.Optional;

public interface AirplanesService {
    List<Airplane> getAllAirplanes();

    Optional<Airplane> getAirplaneById(int id);

    Optional<Airplane> getAirplaneByName(String name);

    int addAirplane(Airplane airplane);

    void updateAirplane(Airplane airplane, int id);

    void deleteAirplane(int id);

}
