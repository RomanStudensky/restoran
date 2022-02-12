package ru.pnzgu.restauran.store;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    BUHGALTER("Бухгалтер"),
    POVAR("Повар"),
    ADMIN("Администратор"),
    OFICIANT("Официант"),
    BARMAN("Бармен");

    String value;

}
