package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.SeatService;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by demerzel on 2018/4/16.
 */
@RestController
@RequestMapping("/seat")
public class SeatController {
    private SeatService seatService;
    private ResultGenerator resultGenerator;

    @Autowired
    public SeatController(SeatService seatService, ResultGenerator resultGenerator) {
        this.seatService = seatService;
        this.resultGenerator = resultGenerator;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseData addSeat(@RequestBody Map map){
        Integer row=Integer.valueOf(map.get("row").toString());
        Integer col=Integer.valueOf(map.get("col").toString());
        Integer hall=Integer.valueOf(map.get("hall").toString());
        System.out.println(row);
        return resultGenerator.getSuccessResult(seatService.addSeat(hall,row,col));
    }
}
