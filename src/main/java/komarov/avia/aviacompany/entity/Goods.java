package komarov.avia.aviacompany.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {
    private int id;
    private int goodsType;
    private int flightId;
    private int amount;
    private BigDecimal profit;
}
