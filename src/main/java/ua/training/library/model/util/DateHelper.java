package ua.training.library.model.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * <p> The Util class.
 * This class provide methods for converting different types of date to each other.
 *
 * @author Alexander Bogomolnyy
 * @version 1.0 10.01.2017.
 */
public class DateHelper {

    public static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * This method converts java.sql.Date into java.time.LocalDate
     *
     * @param date - date
     * @return - date
     */
    public static LocalDate getLocalDate(Date date) {
        return date != null ? date.toLocalDate() : null;
    }

    /**
     * This method converts java.time.LocalDate into java.sql.Date
     *
     * @param date - date
     * @return - date
     */
    public static Date getDate(LocalDate date) {
        return date != null ? Date.valueOf(date) : null;
    }

    /**
     * This method converts date in String format into java.time.LocalDate
     *
     * @param date - date in String
     * @return - date
     */
    public static LocalDate getLocalDateFromString(String date) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return  LocalDate.parse(date, formatter);
    }
}
