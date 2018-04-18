package com.dhu.service;

import com.dhu.model.CommentEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/14.
 */
@Service
public interface CommentService {
    List<Map> findByMovieId(Integer mid);
    CommentEntity findById(Integer id);
    CommentEntity add(CommentEntity commentEntity);
    CommentEntity findByMovieAndUser(Integer movie_id,Integer user_id);
    CommentEntity update(CommentEntity commentEntity);
    Boolean delete(CommentEntity commentEntity);
}
