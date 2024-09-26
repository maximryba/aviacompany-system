package komarov.avia.aviacompany.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsType {
    private int id;
    private String type;
    private BigDecimal profit;
}
