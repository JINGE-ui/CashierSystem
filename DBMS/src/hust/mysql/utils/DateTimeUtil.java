package hust.mysql.utils;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by 戴宪锁 on 2017-05-30.
 */
public class DateTimeUtil {
    public static Timestamp getTime(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}

