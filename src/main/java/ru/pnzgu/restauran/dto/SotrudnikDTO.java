package ru.pnzgu.restauran.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class SotrudnikDTO extends DtoParent {
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
}
