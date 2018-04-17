package com.dhu.service.impl;

import com.dhu.model.CommentEntity;
import com.dhu.repository.CommentRepository;
import com.dhu.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/14.
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Map> findByMovieId(Integer mid){

        List<Object> lst=commentRepository.findByMovie(mid);
        List<Map> reslst=new ArrayList<>();
        for(int i=0;i<lst.size();++i){
            Object[] obj= (Object[]) lst.get(i);
            Map<String,Object> map=new HashMap<>();
            map.put("avatar",(String)obj[0]);
            map.put("name",obj[1]);
            map.put("comment",obj[2]);
            reslst.add(map);
        }
        return reslst;
    }
}
