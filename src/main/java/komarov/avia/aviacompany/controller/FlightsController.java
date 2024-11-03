package komarov.avia.aviacompany.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import komarov.avia.aviacompany.entity.Airport;
import komarov.avia.aviacompany.entity.Flight;
import komarov.avia.aviacompany.entity.FlightSearch;
import komarov.avia.aviacompany.service.AirportsService;
import komarov.avia.aviacompany.service.FlightsService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class FlightsController {

    private final FlightsService flightsService;
    
    private final AirportsService airportsService;

    @GetMapping("flights/list")
    public String findFlights(Model model) {
    	List<Flight> flights = this.flightsService.findAll();
    	List<Airport> airports = this.airportsService.getAllAirports();
        Map<Integer, Airport> airportMap = airports.stream()
            .collect(Collectors.toMap(Airport::getId, Function.identity()));

        model.addAttribute("flights", flights);
        model.addAttribute("airportMap", airportMap);
    	return "flights_list";
    }
    
    @GetMapping("/search")
    public String searchFlights(@ModelAttribute FlightSearch flightSearch, Model model) {
        List<Flight> flights = this.flightsService.findAllBySearch(flightSearch);
        Map<Integer, Airport> airportMap = this.airportsService.getAllAirports().stream()
                .collect(Collectors.toMap(Airport::getId, Function.identity()));

        model.addAttribute("flights", flights);
        model.addAttribute("airportMap", airportMap);

        return "search"; 
    }
}
