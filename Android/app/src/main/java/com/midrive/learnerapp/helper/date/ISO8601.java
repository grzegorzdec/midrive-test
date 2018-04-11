package com.midrive.learnerapp.helper.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by dylanturney on 01/08/2017.
 */

public class ISO8601 {

    public static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    public static String toDayMonthDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        DateFormat dfOutput = new SimpleDateFormat("EEE d MMM");
        return dfOutput.format(calendar.getTime());
    }

    public static String toTimeWithPrefix(Date date) {
        return "At " + toTime(date);
    }

    public static String toTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        DateFormat dfOutput = new SimpleDateFormat("HH:mm");
        return dfOutput.format(calendar.getTime());
    }

    public static String toDayMonthDateTime(Date date) {
        return toDayMonthDate(date) + ", " + toTime(date);
    }
}