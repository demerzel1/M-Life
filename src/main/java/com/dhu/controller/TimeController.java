package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.TimeService;
import com.dhu.utils.CommonUtils;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/14.
 */

@RestController
@CrossOrigin
@RequestMapping("/time")
public class TimeController {

     TimeService timeService;

    private ResultGenerator resultGenerator;

    private CommonUtils commonUtils=new CommonUtils();

    @Autowired
    public TimeController(TimeService timeService,ResultGenerator resultGenerator){
        this.timeService=timeService;
        this.resultGenerator=resultGenerator;
    }

    @RequestMapping(value = "/getTime",method = RequestMethod.POST)
    public ResponseData getTimeByCinemaMovieDate(@RequestBody Map map){
        System.out.println(11111);
        Integer mid=Integer.valueOf(map.get("mid").toString());
        Integer cid=Integer.valueOf(map.get("cid").toString());
        String str=map.get("day").toString();
        Date date1=commonUtils.String2Date(str);
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

    @RequestMapping(value = "/getByCidAndDate",method = RequestMethod.POST)
    public ResponseData getByCinemaAndDate(@RequestBody Map map){
        Integer cid=Integer.valueOf(map.get("cid").toString());
        String str=map.get("day").toString();
        Date date=commonUtils.String2Date(str);
        return resultGenerator.getSuccessResult(timeService.findByCidAndDate(cid,date));
    }

    @RequestMapping(value = "/getMovieByTimeId",method = RequestMethod.POST)
    public ResponseData getMovieByTimeId(@RequestBody Map map){
        Integer tid=Integer.valueOf(map.get("tid").toString());
        return resultGenerator.getSuccessResult(timeService.findMovieById(tid));
    }
}
