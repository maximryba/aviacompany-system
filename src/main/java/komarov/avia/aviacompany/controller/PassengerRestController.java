package komarov.avia.aviacompany.controller;

import komarov.avia.aviacompany.entity.Passenger;
import komarov.avia.aviacompany.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PassengerRestController {

    private final PassengerService passengerService;

    @GetMapping("/api/passengers/{userId}")
    public List<Passenger> getPassengers(@PathVariable int userId) {

        return this.passengerService.findByUserId(userId);
    }

}
