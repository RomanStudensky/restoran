package ru.pnzgu.restauran.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostavshikDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "№",
            "Наименование",
            "Адрес",
            "Телефон"
    );
    private String namePost;
    private String address;
    private String inn;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                String.valueOf(id),
                String.valueOf(namePost),
                String.valueOf(address),
                String.valueOf(inn)
        );
    }
}
