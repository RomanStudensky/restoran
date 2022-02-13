package ru.pnzgu.restauran.util.excel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SpistProdCol {
    COLUMN_NUMBER (0, "№"),
    COLUMN_PROD_NAME(1, "Название продукта"),
    COLUMN_QUANTITY(2, "Количество"),
    COLUMN_REASON(3, "Причина списания"),
    COLUMN_DATE(4, "Дата списания"),
    COLUMN_SUMMA(5, "Стоимость списанного продукта");

    private final int colNum;
    private final String colName;
    public static final int LENGTH = 6;
}
