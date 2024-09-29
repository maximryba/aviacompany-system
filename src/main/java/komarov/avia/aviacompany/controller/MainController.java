package komarov.avia.aviacompany.controller;

import komarov.avia.aviacompany.entity.FlightSearch;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {



    @PostMapping("/")
    public String searchFlights(@ModelAttribute FlightSearch flightSearch, Model model) {
        model.addAttribute("flightSearch", flightSearch);
        return "index";
    }

    @GetMapping("/")
    public String searchFlights(Model model) {
        model.addAttribute("flightSearch", new FlightSearch());
        return "index";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager/main";
    }
}
