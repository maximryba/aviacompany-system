package komarov.avia.aviacompany.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import komarov.avia.aviacompany.service.FlightsService;

import java.util.List;

@RestController
public class FlightsRestController {

    private final FlightsService flightsService;

    public FlightsRestController(FlightsService flightsService) {
        this.flightsService = flightsService;
    }

    @GetMapping("/search/cities")
    public List<String> searchCities(@RequestParam String field, @RequestParam String query) {
        if ("from".equals(field)) {
            return flightsService.findDepartureCity(query);
        } else if ("to".equals(field)) {
            return flightsService.findArrivalCity(query);
        }
        return List.of();
    }
}
