package ru.pnzgu.restauran.util.excel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SostavPostavCol {
    COLUMN_NUMBER (0, "№"),
    COLUMN_PROD_NAME(1, "Название продукта"),
    COLUMN_QUANTITY(2, "Количество"),
    COLUMN_PRICE(3, "Цена"),
    COLUMN_SUMMA(4, "Сумма");

    private int colNum;
    private String colName;
    public static final int LENGTH = 5;
}