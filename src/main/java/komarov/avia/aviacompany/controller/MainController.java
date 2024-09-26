package komarov.avia.aviacompany.controller;

import komarov.avia.aviacompany.entity.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {



    @PostMapping("/search")
    public String searchFlights(@ModelAttribute Flight flight, Model model) {

        return "flights_list";
    }
}
