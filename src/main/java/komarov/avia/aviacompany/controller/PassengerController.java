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

    @PostMapping("/passenger/add/{flightId}")
    public String addPassenger(@ModelAttribute Passenger passenger,
                               @PathVariable int flightId, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            passenger.setUserId(this.userService.getByUsername(userDetails.getUsername()).getId());
            this.passengerService.save(passenger);
            return "redirect:/booking/" + flightId + "/" + this.userService.getByUsername(userDetails.getUsername()).getId();
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Пассажир с таким номером телефона уже зарегистрирован.");
            return "add_passenger";
        }
    }

    @GetMapping("/passenger/add/{flightId}")
    public String addPassenger(@PathVariable int flightId, Model model,
                               @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("currentUser", userDetails);
        model.addAttribute("flightId", flightId);
        return "add_passenger";
    }

}
