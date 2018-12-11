package by.training.provider.util;

import by.training.provider.exception.LogicException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConverter {    //TODO CLASS
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date toDate(String dateString) throws LogicException {                //TODO STATIC
        Date parsed;
        try {
            parsed = new Date(FORMAT.parse(dateString).getTime());
        } catch (ParseException e) {
            throw new LogicException("Incorrect data");             //TODO EXCEPTION
        }
        return parsed;
    }
}
