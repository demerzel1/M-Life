package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.CinemaService;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.support.RestGatewaySupport;

import java.util.Map;

/**
 * Created by demerzel on 2018/4/12.
 */
@RestController
@CrossOrigin
@RequestMapping("/cinema")
public class CinemaController {

    private CinemaService cinemaService;

    private ResultGenerator resultGenerator;

    @Autowired
    public CinemaController(CinemaService cinemaService,ResultGenerator resultGenerator){
        this.cinemaService=cinemaService;
        this.resultGenerator=resultGenerator;
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResponseData getAllCinema(){
        return resultGenerator.getSuccessResult(cinemaService.findAllCinema());
    }

    @RequestMapping(value = "/getByCity",method = RequestMethod.POST)
    public ResponseData getByCity(@RequestBody Map map){
        Integer city=Integer.valueOf(map.get("cid").toString());
        return resultGenerator.getSuccessResult(cinemaService.findCinemaByCity(city));
    }

    @RequestMapping(value = "/getById",method = RequestMethod.POST)
    public ResponseData getById(@RequestBody Map map){
        Integer id=Integer.valueOf(map.get("cid").toString());
        return resultGenerator.getSuccessResult(cinemaService.findById(id));
    }
}
