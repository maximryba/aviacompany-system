package komarov.avia.aviacompany.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Period;
import java.util.Date;

@Data
public class Flight {
    private int id;
    private int departureAirport;
    private int arrivalAirport;
    private Date departureTime;
    private Date arrivalTime;
    private BigDecimal cost;
    private int passengerCount;
    private int distance;
    private Period flightDuration;
    private int airplaneId;
}
