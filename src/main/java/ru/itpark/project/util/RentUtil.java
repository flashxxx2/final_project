package ru.itpark.project.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RentUtil {

    public static LocalDateTime calculateRentEndTime(Long hours){
        return LocalDateTime.now().plusHours(hours);
    }

    public static String localDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        return localDateTime.format(formatter);
    }
}
