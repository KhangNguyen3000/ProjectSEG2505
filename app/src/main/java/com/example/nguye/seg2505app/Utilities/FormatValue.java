package com.example.nguye.seg2505app.Utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatValue {

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
     * Convert a long representing a Date to the format yyyy-mm-dd
     * @param dateLong
     * @return
     */
    public static String longToDate(long dateLong) {
        Date date = new Date(dateLong);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        String dateString = year + "-" + month + "-" + day;
        dateString = dateYMD(dateString);
        return dateString;
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

    /**
     * Convert an int to a time in format 0:00
     * @param time int representing a number of minutes
     * @return the formatted String
     */
    public static String minToTimeString(int time) {
        int hours = time / 60;
        int minutes = time % 60;
        if (minutes < 10) {
            return hours + ":0" + minutes; // Add a leading zero to the minutes
        } else {
            return hours + ":" + minutes;
        }
    }

    /**
     * Convert a time in format 0:00 to an int corresponding to a number of minutes
     * @param timeString String representing a time in format 0:00
     * @return the number of minutes
     */
    public static int timeStringToMin(String timeString) {
        // Find the index of the first colon in the String
        int colon = 0; // Index of the first colon (:) in timeString
        for (int i = 0; i < timeString.length(); i++) {
            if (timeString.charAt(i) == ':') {
                colon = i;
                break;
            }
        }
        if (colon == 0) { // The string definitely has the wrong format
            return -1;
        }
        try {
            String hours = timeString.substring(0, colon);
            String minutes = timeString.substring(colon + 1, colon + 3);

            int h = Integer.parseInt(hours);
            int m = Integer.parseInt(minutes);

            return 60*h + m;
        } catch (IndexOutOfBoundsException ex) {
            return -1;
        }
    }

    /**
     * Increment the given date by one day.
     * @param dateString The date to increment, in format yyyy-mm-dd
     * @return
     */
    public static String incrementDate(String dateString) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            calendar.setTime(df.parse(dateString));
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            return df.format(calendar.getTime());
        } catch (ParseException e) {
            return "0000-00-00";
        }
    }
}
