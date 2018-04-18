package com.dhu.controller;

import com.dhu.model.CommentEntity;
import com.dhu.model.ResponseData;
import com.dhu.service.CommentService;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/14.
 */
@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;
    private ResultGenerator resultGenerator;

    @Autowired
    public CommentController(CommentService commentService,ResultGenerator resultGenerator){
        this.commentService=commentService;
        this.resultGenerator=resultGenerator;
    }

    @RequestMapping(value = "/getByMid",method = RequestMethod.POST)
    public ResponseData getByMid(@RequestBody Map map){
        Integer mid=Integer.valueOf(map.get("mid").toString());
        return resultGenerator.getSuccessResult(commentService.findByMovieId(mid));
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseData add(@RequestBody CommentEntity commentEntity){
        Integer user_id=commentEntity.getUserId();
        Integer movie_id=commentEntity.getMovieId();
        CommentEntity commentEntity1=commentService.findByMovieAndUser(movie_id,user_id);
        if(commentEntity1!=null){
            return resultGenerator.getFailResult("已评论");
        }
        return resultGenerator.getSuccessResult(commentService.add(commentEntity));
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseData delete(@RequestBody CommentEntity commentEntity){
        Integer id=commentEntity.getId();
        CommentEntity commentEntity1=commentService.findById(id);
        if(commentEntity1==null){
            return resultGenerator.getFailResult("此评论不存在");
        }
        if(commentService.delete(commentEntity)){
            return resultGenerator.getSuccessResult();
        }else
            return resultGenerator.getFailResult("删除失败");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseData update(@RequestBody CommentEntity commentEntity){
        Integer id=commentEntity.getId();
        CommentEntity commentEntity1=commentService.findById(id);
        if(commentEntity1==null){
            return resultGenerator.getFailResult("此评论不存在");
        }
        return resultGenerator.getSuccessResult(commentService.update(commentEntity));
    }
}
