package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.ExcelService;
import com.dhu.service.MovieService;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by demerzel on 2018/4/19.
 */
@RestController
@CrossOrigin
@RequestMapping("/excel")
public class ExcelController {
    private ResultGenerator resultGenerator;

    private MovieService movieService;

    private ExcelService excelService;

    @Autowired
    public ExcelController(ResultGenerator resultGenerator, MovieService movieService, ExcelService excelService) {
        this.resultGenerator = resultGenerator;
        this.movieService = movieService;
        this.excelService = excelService;
    }

    @RequestMapping(value = "/uploadMovieExcel",method = RequestMethod.POST)
    public ResponseData uploadExcel(@RequestParam(value="file",required = false)MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        return resultGenerator.getSuccessResult(movieService.addFromExcel(file));
    }

    @RequestMapping(value = "/getCinema",method = RequestMethod.GET)
    public ResponseData getCinema(HttpServletRequest request) throws IOException {
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path=pathRoot+"/static";
        return resultGenerator.getSuccessResult(excelService.getCinemaExcel(path));
    }

    @RequestMapping(value = "/getMovie",method = RequestMethod.GET)
    public ResponseData getMovie(HttpServletRequest request) throws IOException {
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path=pathRoot+"/static";
        return resultGenerator.getSuccessResult(excelService.getMovieExcel(path));
    }

    @RequestMapping(value = "/getTime",method = RequestMethod.GET)
    public ResponseData getTime(HttpServletRequest request) throws IOException {
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path=pathRoot+"/static";
        return resultGenerator.getSuccessResult(excelService.getTimeExcel(path));
    }

    @RequestMapping(value = "/getUser",method = RequestMethod.GET)
    public ResponseData getUser(HttpServletRequest request) throws IOException {
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path=pathRoot+"/static";
        return resultGenerator.getSuccessResult(excelService.getUserExcel(path));
    }
}
