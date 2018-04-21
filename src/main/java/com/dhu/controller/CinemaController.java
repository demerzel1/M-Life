package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.CinemaService;
import com.dhu.service.HallService;
import com.dhu.utils.CommonUtils;
import com.dhu.utils.ResultGenerator;
import org.omg.CORBA.INTERNAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    private HallService hallService;

    @Autowired
    public CinemaController(CinemaService cinemaService, ResultGenerator resultGenerator, HallService hallService){
        this.cinemaService=cinemaService;
        this.resultGenerator=resultGenerator;
        this.hallService = hallService;
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

    @RequestMapping(value = "/getByMovieCityDate",method = RequestMethod.POST)
    public ResponseData getByMovieCityDate(@RequestBody Map map){
        Integer mid=Integer.valueOf(map.get("mid").toString());
        Integer cid=Integer.valueOf(map.get("cityid").toString());
        Date date=CommonUtils.me().String2Date(map.get("day").toString());
        return resultGenerator.getSuccessResult(cinemaService.findByMovieAndDateAndCity(mid,date,cid));
    }

    @RequestMapping(value = "/getHall",method = RequestMethod.POST)
    public ResponseData getHall(@RequestBody Map map){
        Integer cid=Integer.valueOf(map.get("cid").toString());
        return resultGenerator.getSuccessResult(hallService.findByCinemaId(cid));
    }
}
