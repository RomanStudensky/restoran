package ru.pnzgu.restauran.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavProdDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "Блюда/напиток",
            "Количество",
            "Стоимость"
    );

    private MenuDTO bludo;
    private Long quantity = 0L;
    private Double summa = (double) 0;
    private ProdazaDTO prodaza;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                String.valueOf(bludo),
                String.valueOf(quantity),
                String.valueOf(summa)
        );
    }
}
