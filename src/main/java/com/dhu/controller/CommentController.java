package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.CommentService;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/14.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @Autowired
    ResultGenerator resultGenerator;

    @RequestMapping(value = "getByMid",method = RequestMethod.POST)
    public ResponseData getByMid(@RequestBody Map map){
        Integer mid=Integer.valueOf(map.get("mid").toString());
        return resultGenerator.getSuccessResult(commentService.findByMovieId(mid));
    }
}
