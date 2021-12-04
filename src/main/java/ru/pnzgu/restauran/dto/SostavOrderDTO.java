package ru.pnzgu.restauran.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavOrderDTO extends DtoParent {
    private ProductDTO product;
    private OrdersDTO orders;
    private Long quantity = 0L;
}
