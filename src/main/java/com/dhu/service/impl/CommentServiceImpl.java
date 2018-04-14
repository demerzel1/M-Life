package com.dhu.service.impl;

import com.dhu.model.CommentEntity;
import com.dhu.repository.CommentRepository;
import com.dhu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by demerzel on 2018/4/14.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Object> findByMovieId(Integer mid){
        return commentRepository.findByMovie(mid);
    }
}
