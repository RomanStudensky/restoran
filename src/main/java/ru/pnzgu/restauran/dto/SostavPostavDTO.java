package ru.pnzgu.restauran.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavPostavDTO extends DtoParent {
    private ProductDTO product;
    private Long quantity = 0L;
    private Long price = 0L;
    private NakladDTO tovarNaklad;
    private Long summa = 0L;
}
