package komarov.avia.aviacompany.controller.manager;

import komarov.avia.aviacompany.entity.Airplane;
import komarov.avia.aviacompany.service.AirplanesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/manager/airplanes")
public class AirplanesManagerController {
    private final AirplanesService airplanesService;

    @GetMapping("/all")
    public String getAllAirplanes(Model model) {
        List<Airplane> planes = this.airplanesService.getAllAirplanes();
        model.addAttribute("planes", planes);
        return "manager/airplanes";
    }

    @PostMapping("/add")
    public String addAirplane(@ModelAttribute Airplane airplane) {

        this.airplanesService.addAirplane(airplane);
        System.out.println("Plane name: " + airplane.getName());
        System.out.println("Fuel Capacity: " + airplane.getFuelCapacity());
        System.out.println("Passenger Capacity: " + airplane.getPassengerCapacity());
        System.out.println("Service Cost: " + airplane.getServiceCost());
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
    

}
