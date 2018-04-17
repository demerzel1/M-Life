package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.TimeService;
import com.dhu.utils.CommonUtils;
import com.dhu.utils.ResultGenerator;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/14.
 */

@RestController
@RequestMapping("/time")
public class TimeController {

     TimeService timeService;

    private ResultGenerator resultGenerator;

    @Autowired
    public TimeController(TimeService timeService,ResultGenerator resultGenerator){
        this.timeService=timeService;
        this.resultGenerator=resultGenerator;
    }

    @RequestMapping(value = "/getTime",method = RequestMethod.POST)
    public ResponseData getTime(@RequestBody Map map){
        System.out.println(11111);
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        java.util.Date date=null;
        Integer mid=Integer.valueOf(map.get("mid").toString());
        Integer cid=Integer.valueOf(map.get("cid").toString());
        String str=map.get("day").toString();
        try{
            date=format1.parse(str);
        }catch (ParseException e){
        }
        long l=date.getTime();
        date1=new Date(l);
        System.out.println(date1.toString());
        List<Object> list=timeService.findByMidCidDate(mid,cid,date1);
        List<Map> reslist=new ArrayList<>();
        int sz=list.size();
        for(int i=0;i<sz;++i){
            Object[] obj= (Object[]) list.get(i);
            HashMap map1=new HashMap();
            map1.put("time_id",(Integer)obj[0]);
            map1.put("hall_number",(Integer)obj[1]);
            map1.put("start_time",(Timestamp)obj[2]);
            map1.put("cost",(Double)obj[3]);
            reslist.add(map1);
        }
        return resultGenerator.getSuccessResult(reslist);
    }

    @RequestMapping(value = "/getSaledSeat",method = RequestMethod.POST)
    public ResponseData getSaledSeat(@RequestBody Map map){
        Integer time_id=Integer.valueOf(map.get("tid").toString());
        return resultGenerator.getSuccessResult(timeService.findSeatById(time_id));
    }
}
