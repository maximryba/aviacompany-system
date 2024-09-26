package komarov.avia.aviacompany.entity;

import lombok.Data;

@Data
public class Passenger {
    private int id;
    private String firstName;
    private String lastName;
    private int userId;
    private int flightId;
    private int seatId;
}
