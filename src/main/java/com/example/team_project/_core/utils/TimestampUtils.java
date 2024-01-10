package com.example.team_project._core.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimestampUtils {

    public static String timeStampToDate(Timestamp time) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(time);
    }
    public static String timeStampToDate(Timestamp time, String dateFormat) {
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
    	return simpleDateFormat.format(time);
    }
    // 한국 시간대로 현재 날짜와 시간 가져오기
    public static Timestamp findCurrnetTime() {
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Seoul"));
        return Timestamp.valueOf(localDateTime);
    }
    
}
