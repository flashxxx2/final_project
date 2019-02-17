package ru.itpark.project.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RentUtil {

    public static LocalDateTime calculateRentEndTime(Long minutes){
        return LocalDateTime.now().plusMinutes(minutes);
    }

    public static String localDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm");
        return localDateTime.format(formatter);
    }
}
