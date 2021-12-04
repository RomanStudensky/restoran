package ru.pnzgu.restauran.dto;

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
    private BigDecimal summa = BigDecimal.valueOf(0);
    private ProdazaDTO prodaza;
}
