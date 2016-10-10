package seedu.address.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import seedu.address.commons.core.Messages;
import seedu.address.commons.exceptions.IllegalValueException;

public class DateFormatter {

    private static DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy");
    private static DateFormat timeFormatter = new SimpleDateFormat("hhmm");
    
    private static DateFormat dateDisplayFormatter = new SimpleDateFormat("dd MMM yyyy");
    private static DateFormat timeDisplayFormatter = new SimpleDateFormat("hh:mm");
    
    public static Date convertStringToDate(String val) throws IllegalValueException {
        try {
            return dateFormatter.parse(val);
        } catch (ParseException e) {
            throw new IllegalValueException(Messages.MESSAGE_INVALID_DATE_FORMAT);
        }
    }

    public static Date convertStringToTime(String val) throws IllegalValueException {
        try {
            return timeFormatter.parse(val);
        } catch (ParseException e) {
            throw new IllegalValueException(Messages.MESSAGE_INVALID_TIME_FORMAT);
        }
    }

    public static String convertDateToString(Date val) {
        return dateFormatter.format(val);
    }
    
    public static String convertTimeToString(Date val) {
        return timeFormatter.format(val);
    }
    
    public static String convertDateToDisplayString(Date val) {
        return dateDisplayFormatter.format(val);
    }
    
    public static String convertTimeToDisplayString(Date val) {
        return timeDisplayFormatter.format(val);
    }
}