package com.example.nguye.seg2505app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatInput {

    /**
     * Change the string to the format YYYY-MM-DD by adding a leading zero to the month or day
     *  if they are smaller than 10
     * @param dateString
     * @return the formatted string
     */
    public static String dateYMD(String dateString) {
        // Find the index of the two dashes in the String
        int firstDash = 0; // Index of the first dash (-) in dateString
        int lastDash = 0; // Index of the last dash (-) in dateString
        for (int i = 0; i < dateString.length(); i++) {
            if (dateString.charAt(i) == '-') {
                if (firstDash == 0) {
                    firstDash = i;
                } else {
                    lastDash = i;
                    break;
                }
            }
        }

        String year = dateString.substring(0, firstDash);
        String month = dateString.substring(firstDash + 1, lastDash);
        String day = dateString.substring(lastDash + 1);
        if (Integer.parseInt(month) < 10) {
            month = "0" + month;
        }
        if (Integer.parseInt(day) < 10) {
            day = "0" + day;
        }

        return year + "-" + month + "-" + day;
    }


    /**
     * Change the string to the format hh:mm by adding a leading zero to the hours or minutes
     *  if they are smaller than 10
     * @param timeString
     * @return the formatted string
     */
    public static String timeHM(String timeString) {
        // Find the index of the two colons in the String
        int firstColon = 0; // Index of the first colon (:) in timeString
        for (int i = 0; i < timeString.length(); i++) {
            if (timeString.charAt(i) == ':') {
                firstColon = i;
                break;
            }
        }

        String hours = timeString.substring(0, firstColon);
        String minutes = timeString.substring(firstColon + 1);
        if (Integer.parseInt(hours) < 10) {
            hours = "0" + hours;
        }
        if (Integer.parseInt(minutes) < 10) {
            minutes = "0" + minutes;
        }

        return hours + ":" + minutes;
    }

    /**
     * Convert a date string to a long
     * @param dateString
     * @return
     */
    public static long dateToLong(String dateString) {
        // From https://stackoverflow.com/questions/12473550/how-to-convert-a-string-date-to-long-millseconds
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = formatter.parse(dateString);
            long time = date.getTime();
            return time;
        } catch (ParseException e) {
            return (long) 0;
        }
    }

    /**
     * Convert a time string to a long
     * @param timeString
     * @return
     */
    public static long timeToLong(String timeString) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        try {
            Date date = formatter.parse(timeString);
            long time = date.getTime();
            return time;
        } catch (ParseException e) {
            return (long) 0;
        }
    }
}
