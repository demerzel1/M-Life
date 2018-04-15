package com.dhu.utils;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by demerzel on 2018/4/15.
 */
public class CommonUtils {
    public Date getNextDay(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        java.util.Date date1=calendar.getTime();
        long l=date1.getTime();
        date=new Date(l);
        return date;
    }
}
