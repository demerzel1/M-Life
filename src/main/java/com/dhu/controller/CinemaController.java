package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.CinemaService;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.support.RestGatewaySupport;

/**
 * Created by demerzel on 2018/4/12.
 */
@RestController
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
}
