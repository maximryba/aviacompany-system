package komarov.avia.aviacompany.entity;

import lombok.Data;

import java.util.Date;

@Data
public class FlightSearch {
    private String departureCity;
    private String destinationCity;
    private Date departureDate;
    private int passengerCount;
}
