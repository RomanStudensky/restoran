package ru.pnzgu.restauran.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SostavBludoDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "Продукт",
            "Количество"
    );

    private Integer count;
    private ProductDTO product;
    private MenuDTO menu;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                product.getNameProd(),
                String.valueOf(count)
        );
    }
}

