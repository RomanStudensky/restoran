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
    private Long quantity;
    private BigDecimal summa;
    private ProdazaDTO prodaza;
}
