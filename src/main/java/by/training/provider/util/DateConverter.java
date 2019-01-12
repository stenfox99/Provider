package by.training.provider.util;

import by.training.provider.exception.LogicException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * The Class DateConverter.
 */
public class DateConverter {
    
    /** The Constant FORMAT. */
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Instantiates a new date converter.
     */
    private DateConverter(){}

    /**
     * To date.
     *
     * @param dateString the date string
     * @return the date
     * @throws LogicException the logic exception
     */
    public static Date toDate(String dateString) throws LogicException {
        Date parsed;
        try {
            parsed = new Date(FORMAT.parse(dateString).getTime());
        } catch (ParseException e) {
            throw new LogicException("Incorrect data");
        }
        return parsed;
    }
}
