package com.example.interview.utils;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

@Component
public class BasicUtil {

    public String utc2LocalTime(String utc){
        String resDate = "";
        try {
            SimpleDateFormat utcFormat = new SimpleDateFormat("MMM d, yyyy HH:mm:ss z", Locale.ENGLISH);
            Date utcTime = utcFormat.parse(utc);
            DateFormat localFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            resDate =  localFormat.format(utcTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resDate;
    }
}
