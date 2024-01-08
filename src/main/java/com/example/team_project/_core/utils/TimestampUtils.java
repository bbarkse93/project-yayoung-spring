package com.example.team_project._core.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampUtils {

    public static String timeStampToDate(Timestamp time, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(time);
    }
}
