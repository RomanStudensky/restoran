package ru.pnzgu.restauran.dto;

import lombok.*;

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
