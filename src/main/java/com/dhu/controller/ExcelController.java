package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.MovieService;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by demerzel on 2018/4/19.
 */
@RestController
public class ExcelController {
    private ResultGenerator resultGenerator;

    private MovieService movieService;

    @Autowired
    public ExcelController(ResultGenerator resultGenerator, MovieService movieService) {
        this.resultGenerator = resultGenerator;
        this.movieService = movieService;
    }

    @RequestMapping(value = "/uploadMovieExcel",method = RequestMethod.POST)
    public ResponseData uploadExcel(@RequestParam(value="file",required = false)MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        return resultGenerator.getSuccessResult(movieService.addFromExcel(file));
    }
}
