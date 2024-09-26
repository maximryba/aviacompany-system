package komarov.avia.aviacompany.entity;

import lombok.Data;

@Data
public class Airplane {
    private int id;
    private int fuelCapacity;
    private int passengerCapacity;
    private String name;
    private int serviceCost;
}
