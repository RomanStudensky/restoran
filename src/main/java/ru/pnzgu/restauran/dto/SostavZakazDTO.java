package ru.pnzgu.restauran.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SostavZakazDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "Блюдо",
            "Количество",
            "Стоимость"
    );

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                bludo.getBludo(),
                String.valueOf(quantity),
                String.valueOf(summa)
        );
    }

    private MenuDTO bludo;
    private Long quantity = 0L;
    private BigDecimal summa = BigDecimal.valueOf(0);
    private ZakazDTO zakaz;
}