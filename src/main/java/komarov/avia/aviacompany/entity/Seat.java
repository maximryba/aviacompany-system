package komarov.avia.aviacompany.entity;

import lombok.Data;

@Data
public class Seat {
    private int id;
    private int airplaneId;
    private int type;
    private int number;
}
