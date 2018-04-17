package com.dhu.utils;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by demerzel on 2018/4/15.
 */
public class CommonUtils {
    public static CommonUtils me(){
        return new CommonUtils();
    }

    public Date getNextDay(Date date){
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,1);
        java.util.Date date1=calendar.getTime();
        long l=date1.getTime();
        date=new Date(l);
        return date;
    }

    public Date String2Date(String str){
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        java.util.Date date=null;
        try{
            date=format1.parse(str);
        }catch (ParseException e){
        }
        long l=date.getTime();
        date1=new Date(l);
        return date1;
    }
}
