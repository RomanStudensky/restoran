package ru.pnzgu.restauran.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdazaDTO extends DtoParent { ;
    private LocalDate dateProd;
    private LocalTime timeProd;
    private SotrudnikDTO sotrud;
    private BigDecimal summa;

}
