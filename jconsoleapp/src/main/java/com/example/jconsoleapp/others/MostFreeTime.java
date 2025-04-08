package com.example.jconsoleapp.others;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.jconsoleapp.MyHelper.p;

// #Most Free Time @ Coderbyte
public class MostFreeTime {

    /*
    The Problem:

    Have the function MostFreeTime(strArr) read the strArr parameter being passed which will represent a full day and will be filled with events that span from time X to time Y in the day. The format of each event will be hh:mmAM/PM-hh:mmAM/PM.

    For example, strArr may be ["10:00AM-12:30PM","02:00PM-02:45PM","09:10AM-09:50AM"].
    Your program will have to output the longest amount of free time available between the start of your first event and the end of your last event in the format: hh:mm.

    The start event should be the earliest event in the day and the latest event should be the latest event in the day.

    The output for the previous input would therefore be 01:30 (with the earliest event in the day starting at 09:10AM and the latest event ending at 02:45PM). The input will contain at least 3 events and the events may be out of order.


    Examples:

    "12:15PM-02:00PM", "09:00AM-10:00AM", "10:30AM-12:00PM"  -->  "00:30"

    "12:15PM-02:00PM", "09:00AM-12:11PM", "02:02PM-04:00PM"  -->  "00:04"
     */

    public static void preMostFreeTime() {
        String[] strArr = {"10:00AM-12:30PM", "02:00PM-02:45PM", "09:10AM-09:50AM"}; // 01:30
//        String[] strArr = {"12:15PM-02:00PM", "09:00AM-10:00AM", "10:30AM-12:00PM"}; // 00:30
//        String[] strArr = {"12:15PM-02:00PM", "09:00AM-12:11PM", "02:02PM-04:00PM"}; // 00:04

//        p(mostFreeTime(strArr));
        p(mostFreeTime2(strArr));
    }

    /*
    Wrong. Combines free times and calculates most free time of all day.
     */
    public static String mostFreeTime(String[] strArr) {

        String[] arr = new String[strArr.length * 2];
        int i = 0; // reusable

        for (String s : strArr) {
            int j = s.indexOf("-");
            arr[i] = s.substring(0, j);
            i++;
            arr[i] = s.substring(j + 1, s.length());
            i++;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mma", Locale.getDefault()); // reusable
        Date date = new Date(); // reusable

        long[] times = new long[arr.length];
        i = 0;

        try {
            for (String s : arr) {
                date = sdf.parse(s);
                times[i] = date.getTime();
                i++;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Arrays.sort(times);

        long resultL = 0;

        for (int j = 2; j < times.length; j += 2) {
            long temp = times[j] - times[j - 1];
            if (temp > 0) // to handle overlapping event times
                resultL += temp;
        }

        date = new Date(resultL);
        sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

        return sdf.format(date);
    }


    /*
    Correct algorithm but needs a fix. Calculates most free time of among all free times.
    It adds 2 hours to the output. To fix it probably I should change the timezone of the sdf or utilize a Calendar object instead of Date.
     */
    public static String mostFreeTime2(String[] strArr) {

        String[] arr = new String[strArr.length * 2];
        int i = 0; // reusable

        for (String s : strArr) {
            int j = s.indexOf("-");
            arr[i] = s.substring(0, j);
            i++;
            arr[i] = s.substring(j + 1, s.length());
            i++;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mma", Locale.getDefault()); // reusable
        Date date = new Date(); // reusable

        long[] times = new long[arr.length];
        i = 0;

        try {
            for (String s : arr) {
                date = sdf.parse(s);
                times[i] = date.getTime();
                i++;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Arrays.sort(times);

        long resultL = 0;

        for (int j = 2; j < times.length; j += 2) {
            // calculate free time
            long temp = times[j] - times[j - 1];
            // handle overlapping event times
            if (temp > 0 &&
                    // compare to store the largest free time
                    temp > resultL)
                resultL = temp;
        }

        date = new Date(resultL);
        sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

        return sdf.format(date);
    }
}
