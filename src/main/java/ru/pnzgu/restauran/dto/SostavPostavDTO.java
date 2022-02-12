package ru.pnzgu.restauran.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavPostavDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "Продукт",
            "Количество",
            "Цена",
            "Стоимость"
    );

    private ProductDTO product;
    private Long quantity = 0L;
    private Long price = 0L;
    private NakladDTO tovarNaklad;
    private Long summa = 0L;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                String.valueOf(product.getNameProd()),
                String.valueOf(quantity),
                String.valueOf(price),
                String.valueOf(summa)
        );
    }
}
