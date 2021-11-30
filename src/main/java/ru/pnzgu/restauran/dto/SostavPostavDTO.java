package ru.pnzgu.restauran.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavPostavDTO extends DtoParent {
    private ProductDTO product;
    private Long quantity;
    private BigDecimal price;
    private SostavNakladDTO tovarNaklad;
    private BigDecimal summa;
}
