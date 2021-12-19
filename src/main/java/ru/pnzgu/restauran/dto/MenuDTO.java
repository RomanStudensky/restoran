package ru.pnzgu.restauran.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO extends DtoParent {
    private String bludo;
    private Long weight = 0L;
    private BigDecimal price = BigDecimal.valueOf(0);
    private CategoryDTO category;

}
