package ru.pnzgu.restauran.util.excel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MenuCol {
    COLUMN_NAME (0, "Название блюда"),
    COLUMN_SOSATAV(0, "Состав"),
    COLUMN_WEIGHT(6, "Вес гр."),
    COLUMN_PRICE(7, "Цена");

    private final int colNum;
    private final String colName;
    public static final int LENGTH = 8;
}