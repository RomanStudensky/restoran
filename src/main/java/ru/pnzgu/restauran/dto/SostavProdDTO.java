package ru.pnzgu.restauran.dto;

import liquibase.pro.packaged.D;
import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavProdDTO extends DtoParent {
    private MenuDTO bludo;
    private Long quantity = 0L;
    private Double summa = (double) 0;
    private ProdazaDTO prodaza;
}
