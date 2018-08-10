package util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DataUtil {

    public static Date getCurrentDate() {
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
