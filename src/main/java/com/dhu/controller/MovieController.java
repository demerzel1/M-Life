package com.dhu.controller;

import com.dhu.model.MovieEntity;
import com.dhu.model.ResponseData;
import com.dhu.service.MovieService;
import com.dhu.utils.Jacksons.Jacksons;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/12.
 */
@RestController
@CrossOrigin
@RequestMapping("/movie")
public class MovieController {
    private MovieService movieService;
    private ResultGenerator resultGenerator;

    @Autowired
    public MovieController(MovieService movieService,ResultGenerator resultGenerator){
        this.movieService=movieService;
        this.resultGenerator=resultGenerator;
    }
    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResponseData getAll(){
        return resultGenerator.getSuccessResult("success",movieService.findAllMovie());
    }

    @RequestMapping(value = "/getById",method = RequestMethod.POST)
    public ResponseData getById(@RequestBody Map map){
        System.out.println(Jacksons.me().readAsString(map));
        Integer mid=Integer.valueOf(map.get("mid").toString());
        System.out.println(mid);
       ResponseData responseData= resultGenerator.getSuccessResult(movieService.findMovieById(mid));
        System.out.println(Jacksons.me().readAsString(responseData));
        return responseData;
    }

    @RequestMapping(value = "/getByDate",method = RequestMethod.POST)
    public ResponseData getByDate(@RequestBody Map map){
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1;
        java.util.Date date=null;
        String str=map.get("day").toString();
        try{
            date=format1.parse(str);
        }catch (ParseException e){
        }
        long l=date.getTime();
        date1=new Date(l);
        return resultGenerator.getSuccessResult(movieService.findAllMovieByDate(date1));
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseData addMovie(@RequestBody MovieEntity movieEntity){
        System.out.println();
        return resultGenerator.getSuccessResult( movieService.addMovie(movieEntity));
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseData updateMovie(@RequestBody MovieEntity movieEntity){
        System.out.println(Jacksons.me().readAsString(movieEntity));
        return resultGenerator.getSuccessResult(movieService.updateMovie(movieEntity));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseData delete(@RequestBody Map map){
        Integer mid=Integer.valueOf(map.get("mid").toString());
        if(movieService.findMovieById(mid)==null){
            return resultGenerator.getFailResult("电影不存在");
        }
           System.out.println(111);
        if(movieService.deleteMovieById(mid)==true){
            System.out.println("true");
            return resultGenerator.getSuccessResult();
        }
        else
            return resultGenerator.getFailResult("删除失败");
    }

    @RequestMapping(value = "/getWatched",method = RequestMethod.POST)
    public ResponseData findByUserId(@RequestBody Map map){
        Integer uid=Integer.valueOf(map.get("uid").toString());
        return resultGenerator.getSuccessResult( movieService.findWatchedByUserId(uid));
    }

    @RequestMapping(value = "/getNotOn",method = RequestMethod.GET)
    public ResponseData getNotOn(){
        return resultGenerator.getSuccessResult(movieService.findNotOn());
    }
}
