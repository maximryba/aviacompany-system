package komarov.avia.aviacompany.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Bucket {
    private int id;
    private int user_id;
    private BigDecimal sum;
}
