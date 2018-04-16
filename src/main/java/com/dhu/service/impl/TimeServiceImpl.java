package com.dhu.service.impl;

import com.dhu.repository.TimeRepository;
import com.dhu.service.TimeService;
import com.dhu.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.List;

/**
 * Created by demerzel on 2018/4/14.
 */
@Service
public class TimeServiceImpl implements TimeService {
    private CommonUtils commonUtils;

    TimeRepository timeRepository;

    @Autowired
    public TimeServiceImpl(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @Override
    public List<Object> findByMidCidDate(Integer mid, Integer cid,Date date){
        Date date2=commonUtils.getNextDay(date);
        return timeRepository.findByMCD(mid,cid,date,date2);
    }
}
