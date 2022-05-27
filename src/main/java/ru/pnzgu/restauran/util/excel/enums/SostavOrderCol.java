package ru.pnzgu.restauran.util.excel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SostavOrderCol {
    COLUMN_NUMBER (0, "№"),
    COLUMN_DRINK_NAME(1, "Название продукта"),
    COLUMN_QUANTITY(2, "Количество");

    private final int colNum;
    private final String colName;
    public static final int LENGTH = 3;
}