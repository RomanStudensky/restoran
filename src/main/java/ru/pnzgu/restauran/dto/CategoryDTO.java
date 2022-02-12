package ru.pnzgu.restauran.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO extends DtoParent implements DtoInterface{

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "№",
            "Название"
    );

    private String nameCat;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                String.valueOf(id),
                nameCat
        );
    }
}
