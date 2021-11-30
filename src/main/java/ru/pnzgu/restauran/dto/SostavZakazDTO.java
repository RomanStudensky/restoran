package ru.pnzgu.restauran.dto;

import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavZakazDTO extends DtoParent {
    private MenuDTO bludo;
    private Long quantity = 0L;
    private BigDecimal summa = BigDecimal.valueOf(0);
    private ZakazDTO zakaz;
}