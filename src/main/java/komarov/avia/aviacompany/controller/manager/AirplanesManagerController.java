package komarov.avia.aviacompany.controller.manager;

import komarov.avia.aviacompany.entity.Airplane;
import komarov.avia.aviacompany.service.AirplanesService;
import komarov.avia.aviacompany.service.SeatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/airplanes")
public class AirplanesManagerController {
    private final AirplanesService airplanesService;

    private final SeatsService seatsService;

    @GetMapping("/all")
    public String getAllAirplanes(Model model) {
        List<Airplane> planes = this.airplanesService.getAllAirplanes();
        model.addAttribute("planes", planes);
        return "manager/airplanes";
    }

    @PostMapping("/add")
    public String addAirplane(@ModelAttribute Airplane airplane) {

        this.airplanesService.addAirplane(airplane);
        return "redirect:/manager/airplanes/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteAirport(@PathVariable int id) {
        this.airplanesService.deleteAirplane(id);
        return "redirect:/manager/airplanes/all";
    }

    @PostMapping("/edit/{id}")
    public String editAirplane(@PathVariable int id, @ModelAttribute Airplane airplane) {
        this.airplanesService.updateAirplane(airplane, id);
        return "redirect:/manager/airplanes/all";
    }

    @PostMapping("/add-seats")
    public String addSeats(@RequestParam Integer airplaneId) {
        Optional<Airplane> airplaneSearch = this.airplanesService.getAirplaneById(airplaneId);
        this.seatsService.addSeats(airplaneSearch.get().getPassengerCapacity() , airplaneSearch.get().getId());
        return "redirect:/manager/airplanes/all";
    }
    

}
