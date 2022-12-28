package com.epay.EPayment.Time;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeManager {
    public static Date getTime() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String date = time.format(formatter);
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date finalDate = null;
        try {
            finalDate = format.parse(date);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return finalDate;
    }
}
