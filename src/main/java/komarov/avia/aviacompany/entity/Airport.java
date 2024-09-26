package komarov.avia.aviacompany.entity;

import lombok.Data;

@Data
public class Airport {
    private int id;
    private String name;
    private String city;
    private String country;
    private boolean goodsService;
}
