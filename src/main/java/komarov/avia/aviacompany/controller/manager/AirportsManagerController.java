package komarov.avia.aviacompany.controller.manager;

import komarov.avia.aviacompany.entity.Airport;
import komarov.avia.aviacompany.service.AirportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manager/airports")
@RequiredArgsConstructor
public class AirportsManagerController {

    private final AirportsService airportsService;

    @GetMapping("/all")
    public String getAllAirports(Model model) {
        List<Airport> airports = this.airportsService.getAllAirports();
        model.addAttribute("airports", airports);
        return "manager/airports";
    }

    @PostMapping("/add")
    public String addAirport(@ModelAttribute("airport") Airport airport) {
        this.airportsService.addAirport(airport);
        return "redirect:all";
    }

    @PostMapping("/edit/{airportId}")
    public String updateAirport(@ModelAttribute("airport") Airport airport,
                                @PathVariable int airportId) {
        this.airportsService.updateAirport(airport, airportId);
        return "redirect:/manager/airports/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteAirport(@PathVariable int id) {
        this.airportsService.deleteAirport(id);
        return "redirect:/manager/airports/all";
    }
}
