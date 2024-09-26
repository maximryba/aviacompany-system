package komarov.avia.aviacompany.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SeatType {
    private int id;
    private SeatTypeEnum type;
    private BigDecimal costCoeff;
}
