package com.dhu.controller;

import com.dhu.model.MovieEntity;
import com.dhu.model.ResponseData;
import com.dhu.service.MovieService;
import com.dhu.utils.Jacksons.Jacksons;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/12.
 */
@RestController
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
        Integer mid=Integer.valueOf(map.get("mid").toString());
        return resultGenerator.getSuccessResult(movieService.findMovieById(mid));
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseData updateMovie(@RequestBody MovieEntity movieEntity){
        System.out.println(Jacksons.me().readAsString(movieEntity));
        return resultGenerator.getSuccessResult(movieService.updateMovie(movieEntity));
    }
}
