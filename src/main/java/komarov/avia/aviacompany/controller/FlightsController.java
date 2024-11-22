package komarov.avia.aviacompany.controller;

import komarov.avia.aviacompany.entity.Passenger;
import komarov.avia.aviacompany.entity.Seat;
import komarov.avia.aviacompany.service.PassengerService;
import komarov.avia.aviacompany.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import komarov.avia.aviacompany.entity.Airport;
import komarov.avia.aviacompany.entity.Flight;
import komarov.avia.aviacompany.entity.FlightSearch;
import komarov.avia.aviacompany.service.AirportsService;
import komarov.avia.aviacompany.service.FlightsService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class FlightsController {

    private final FlightsService flightsService;
    
    private final AirportsService airportsService;

    private final PassengerService passengerService;

    private final UserService userService;

    @GetMapping("flights/list")
    public String findFlights(Model model, @AuthenticationPrincipal UserDetails userDetails) {
    	List<Flight> flights = this.flightsService.findAll();
    	List<Airport> airports = this.airportsService.getAllAirports();
        Map<Integer, Airport> airportMap = airports.stream()
            .collect(Collectors.toMap(Airport::getId, Function.identity()));

        model.addAttribute("flights", flights);
        model.addAttribute("airportMap", airportMap);
        model.addAttribute("currentUser", userDetails);
    	return "flights_list";
    }
    
    @GetMapping("/search")
    public String searchFlights(@ModelAttribute FlightSearch flightSearch, Model model, @AuthenticationPrincipal UserDetails userDetails) {
        List<Flight> flights = this.flightsService.findAllBySearch(flightSearch);
        Map<Integer, Airport> airportMap = this.airportsService.getAllAirports().stream()
                .collect(Collectors.toMap(Airport::getId, Function.identity()));
        model.addAttribute("userId", this.userService.getCurrentUser().getId());
        model.addAttribute("currentUser", userDetails);
        model.addAttribute("flights", flights);
        model.addAttribute("airportMap", airportMap);

        return "search"; 
    }

    @GetMapping("/booking/{flightId}/{userId}")
    public String bookFlight(@PathVariable int flightId, Model model, @AuthenticationPrincipal UserDetails userDetails,
    @PathVariable int userId) {
        List<Passenger> passengers = this.passengerService.findByUserId(this.userService.getByUsername(userDetails.getUsername()).getId());
        Optional<Flight> flight = this.flightsService.findById(flightId);

        model.addAttribute("passengers", passengers);
        model.addAttribute("currentUser", userDetails);
        model.addAttribute("flight", flight.orElse(null));
        return "booking";
    }

    @GetMapping("/booking/{flightId}/{userId}")
    public String getBookingPage(@PathVariable Long flightId, Model model) {
        Flight flight = flightService.getFlightById(flightId);
        List<Seat> seats = seatService.getAvailableSeats(flightId);
        model.addAttribute("flight", flight);
        model.addAttribute("seats", seats);
        model.addAttribute("seatSelection", new SeatSelection());
        return "booking";

    @PostMapping("/booking/confirm")
    public String confirmBooking(@ModelAttribute SeatSelection seatSelection, Model model) {
        // Логика для обработки выбранных мест
        boolean isSuccess = seatService.bookSeats(seatSelection);
        model.addAttribute("isSuccess", isSuccess);
        return "confirmation";
    }
}