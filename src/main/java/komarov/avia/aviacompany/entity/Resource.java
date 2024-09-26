package komarov.avia.aviacompany.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Resource {
    private int id;
    private ResourceType resourceType;
    private BigDecimal cost;
    private int flightId;
}
