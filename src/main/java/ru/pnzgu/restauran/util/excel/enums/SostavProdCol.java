package ru.pnzgu.restauran.util.excel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SostavProdCol {
    COLUMN_NUMBER (0, "№"),
    COLUMN_DRINK_NAME(1, "Название напитка"),
    COLUMN_QUANTITY(2, "Количество"),
    COLUMN_PRICE(3, "Стоимость за единицу"),
    COLUMN_SUMMA(4, "Сумма");

    private int colNum;
    private String colName;
    public static final int LENGTH = 5;
}