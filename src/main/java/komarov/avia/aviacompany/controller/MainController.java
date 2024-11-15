package komarov.avia.aviacompany.controller;

import komarov.avia.aviacompany.entity.FlightSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MainController {

    @PostMapping("/")
    public String searchFlights(@RequestBody FlightSearch flightSearch, Model model) {
        model.addAttribute("flightSearch", flightSearch);
        return "index";
    }

    @GetMapping("/")
    public String searchFlights(Model model, @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("flightSearch", new FlightSearch());
        if (user != null) {
            model.addAttribute("currentUser", user);
        }
        return "index";
    }


    @GetMapping("/manager")
    public String manager(Model model, @AuthenticationPrincipal UserDetails user) {
        model.addAttribute("currentUser", user);
        return "manager/main";
    }

}
