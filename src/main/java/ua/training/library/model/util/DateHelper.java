package ua.training.library.model.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    public static LocalDate getLocalDate(Date date) {
        return date != null ? date.toLocalDate() : null;
    }

    public static Date getDate(LocalDate date) {
        return date != null ? Date.valueOf(date) : null;
    }

    public static LocalDate getLocalDateFromString(String date) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return  LocalDate.parse(date, formatter);
    }
}
