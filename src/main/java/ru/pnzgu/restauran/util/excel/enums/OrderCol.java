package ru.pnzgu.restauran.util.excel.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum OrderCol {
    COLUMN_NUMBER (0, "Номер заявки"),
    COLUMN_DATE(1, "Дата заявки"),
    COLUMN_POSTAVSHIK(2, "Поставщик"),
    COLUMN_ORDER(3, "Договор");

    private final int colNum;
    private final String colName;
    public static final int LENGTH = 4;
}