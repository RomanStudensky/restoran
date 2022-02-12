package ru.pnzgu.restauran.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavOrderDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "Продукт",
            "Количество"
    );

    private ProductDTO product;
    private OrdersDTO orders;
    private Long quantity = 0L;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                product.getNameProd(),
                String.valueOf(quantity)
        );
    }
}
