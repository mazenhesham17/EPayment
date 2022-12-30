package com.epay.EPayment.Time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TimeManager {
    public static String getTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = time.format(formatter);
        return date;
    }
}
