package ru.pnzgu.restauran.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MenuDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "Блюдо",
            "Вес",
            "Стоимость"
    );

    private String bludo;
    private Long weight = 0L;
    private BigDecimal price = BigDecimal.valueOf(0);
    private CategoryDTO category;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                bludo,
                String.valueOf(weight),
                String.valueOf(price)
        );
    }
}
