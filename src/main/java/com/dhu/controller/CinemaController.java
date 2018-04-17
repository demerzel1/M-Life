package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.CinemaService;
import com.dhu.utils.CommonUtils;
import com.dhu.utils.JWTResult;
import com.dhu.utils.JWTUtils;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpServletRequest;
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


    @Autowired
    public CinemaController(CinemaService cinemaService, ResultGenerator resultGenerator){
        this.cinemaService=cinemaService;
        this.resultGenerator=resultGenerator;
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResponseData getAllCinema(@RequestHeader("uid") String uid,@RequestHeader("token") String token){
        JWTResult jwtResult=JWTUtils.checkToken(token);
        if(jwtResult.isStatus()){
            if(jwtResult.getUid()!=uid){
                return resultGenerator.getFailResult("伪造token");
            }
        }else{
            return resultGenerator.getFailResult(jwtResult.getMsg());
        }
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
}
