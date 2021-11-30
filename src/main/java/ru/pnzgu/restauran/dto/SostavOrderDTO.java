package ru.pnzgu.restauran.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavOrderDTO extends DtoParent {
    private ProductDTO product;
    private OrdersDTO orders;
    private Long quantity;
}
