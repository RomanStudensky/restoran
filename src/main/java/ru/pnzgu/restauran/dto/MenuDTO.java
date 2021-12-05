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
    private Long weight = 0L;
    private BigDecimal price = BigDecimal.valueOf(0);
    private CategoryDTO category;

}
