package komarov.avia.aviacompany.controller.manager;

import komarov.avia.aviacompany.entity.Airplane;
import komarov.avia.aviacompany.entity.Airport;
import komarov.avia.aviacompany.entity.Flight;
import komarov.avia.aviacompany.service.AirplanesService;
import komarov.avia.aviacompany.service.AirportsService;
import komarov.avia.aviacompany.service.FlightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/flights")
public class FlightsManagerController {

    private final AirportsService airportsService;

    private final AirplanesService airplanesService;

    private final FlightsService flightsService;

    @GetMapping("/all")
    public String getFlights(Model model) {
        List<Airport> airports = this.airportsService.getAllAirports();
        List<Airplane> airplanes = this.airplanesService.getAllAirplanes();
        List<Flight> flights = this.flightsService.findAll();

        model.addAttribute("airports", airports);
        model.addAttribute("airplanes", airplanes);
        model.addAttribute("flights", flights);

        return "manager/flights";
    }

    @PostMapping("/add")
    public String addFlight(@ModelAttribute("flight") Flight flight) {
        this.flightsService.save(flight);
        return "redirect:/manager/flights/all";
    }

    @PostMapping("/edit/{id}")
    public String updateFlight(@ModelAttribute("flight") Flight flight, @PathVariable int id) {
        this.flightsService.update(id, flight);
        return "redirect:/manager/flights/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteFlight(@PathVariable int id) {
        this.flightsService.delete(id);
        return "redirect:/manager/flights/all";
    }

}
