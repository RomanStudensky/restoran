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
public class MenuDTO extends DtoParent {
    private String bludo;
    private Long weight;
    private BigDecimal price;
    private CategoryDTO category;

}
