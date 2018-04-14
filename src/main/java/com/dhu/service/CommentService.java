package com.dhu.service;

import com.dhu.model.CommentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by demerzel on 2018/4/14.
 */
@Service
public interface CommentService {
    List<Object> findByMovieId(Integer mid);
}
