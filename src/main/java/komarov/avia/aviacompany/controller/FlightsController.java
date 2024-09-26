package komarov.avia.aviacompany.controller;

import komarov.avia.aviacompany.entity.Flight;
import komarov.avia.aviacompany.service.FlightsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightsController {

    private final FlightsService flightsService;

    @GetMapping("list")
    public String list(Model model) {
        List<Flight> allFlights = this.flightsService.findAll();
        model.addAttribute("flights", allFlights);
        return "flights_list";
   }

}
