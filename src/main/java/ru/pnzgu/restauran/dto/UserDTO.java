package ru.pnzgu.restauran.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserDTO extends DtoParent implements DtoInterface {

    @Getter(value = AccessLevel.PRIVATE)
    private static final List<String> HEADER = List.of(
            "№",
            "ФИО",
            "Должность"
    );
    public static final String[] ROLE = new String[] {
            "Администратор",
            "Бухгалтер",
            "Повар",
            "Администратор",
            "Официант",
            "Бармен"
    };

    private String fio;
    private String post;

    @Override
    public List<String> getHeaderList() {
        return HEADER;
    }

    @Override
    public List<String> getElementList() {
        return List.of(
                String.valueOf(id),
                String.valueOf(fio),
                String.valueOf(post)
        );
    }
}
