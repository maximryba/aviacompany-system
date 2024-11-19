package komarov.avia.aviacompany.controller;

import komarov.avia.aviacompany.entity.Passenger;
import komarov.avia.aviacompany.entity.User;
import komarov.avia.aviacompany.service.PassengerService;
import komarov.avia.aviacompany.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    private final UserService userService;

    @PostMapping("/passenger/add/{username}/{flightId}")
    public String addPassenger(@PathVariable("username") String username, @ModelAttribute Passenger passenger,
                               @PathVariable int flightId) {
        passenger.setUserId(this.userService.getByUsername(username).getId());
        this.passengerService.save(passenger);
        return "redirect:/booking/" + flightId;
    }

    @GetMapping("/passenger/add/{username}/{flightId}")
    public String addPassenger(@PathVariable("username") String username, @PathVariable int flightId, Model model,
                               @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("currentUser", userDetails);
        return "add_passenger";
    }

}
