package komarov.avia.aviacompany.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Order {
    private int id;
    private int flightId;
    private int userId;
    private Date creationDate;
    private State state;
    private Date modifiedDate;
}
