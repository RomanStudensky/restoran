package ru.pnzgu.restauran.util.mapping;

import java.time.format.DateTimeFormatter;

/**
 * Опции для полей типа Date
 */
public abstract class DateOptions {

    public static final String PATTERN = "yyyy-MM-dd";

    public static final String TIMEZONE = "Europe/Moscow";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);

}
