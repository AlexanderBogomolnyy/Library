package ua.training.library.model.util;

import java.sql.Date;
import java.time.LocalDate;

public class DateHelper {

    public static LocalDate getLocalDate(Date date) {
        return date != null ? date.toLocalDate() : null;
    }

    public static Date getDate(LocalDate date) {
        return date != null ? Date.valueOf(date) : null;
    }
}
