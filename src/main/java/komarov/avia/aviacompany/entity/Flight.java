package komarov.avia.aviacompany.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.time.Period;

@Data
public class Flight {
    private int id;
    private int departureAirport;
    private int arrivalAirport;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime departureTime;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime  arrivalTime;
    private BigDecimal cost;
    private int passengerCount;
    private int distance;
    private String flightDuration;
    private int airplaneId;
}
