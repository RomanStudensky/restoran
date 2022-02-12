package ru.pnzgu.restauran.util.mapping;

import java.time.format.DateTimeFormatter;

public abstract class TimeOptions {

    public static final String PATTERN = "hh:mm";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern(PATTERN);
}
