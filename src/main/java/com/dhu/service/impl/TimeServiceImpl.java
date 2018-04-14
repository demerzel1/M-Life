package com.dhu.service.impl;

import com.dhu.repository.TimeRepository;
import com.dhu.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by demerzel on 2018/4/14.
 */
@Service
public class TimeServiceImpl implements TimeService {
    @Autowired
    TimeRepository timeRepository;

    @Override
    public List<Object> findByMidCidDate(Integer mid, Integer cid, Date date1){
        return timeRepository.findByMidCidDate(mid,cid,date1);
    }
}
