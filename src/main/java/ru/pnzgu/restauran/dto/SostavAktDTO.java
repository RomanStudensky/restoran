package ru.pnzgu.restauran.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavAktDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "Продукт",
            "Причина списания",
            "Количество",
            "Цена",
            "Стоимость"
    );

    private ProductDTO product;
    private String reason;
    private Integer quantity = 0;
    @JsonProperty(value = "akt")
    private AktDTO aktSpis;
    private Double price = 0.0D;
    private Double summa = 0.0D;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                product.getNameProd(),
                reason,
                String.valueOf(quantity),
                String.valueOf(price),
                String.valueOf(summa)
        );
    }
}
