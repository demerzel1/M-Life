package com.dhu.service.impl;

import com.dhu.model.TimeEntity;
import com.dhu.repository.TimeRepository;
import com.dhu.service.TimeService;
import com.dhu.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/14.
 */
@Service
public class TimeServiceImpl implements TimeService {
    private CommonUtils commonUtils=new CommonUtils();

    private TimeRepository timeRepository;

    @Autowired
    public TimeServiceImpl(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @Override
    public List<Object> findByMidCidDate(Integer mid, Integer cid, Date date){
        System.out.println(112414);
        Date date2=commonUtils.getNextDay(date);
        System.out.println(date2.toString());
        return timeRepository.findByMCD(mid,cid,date,date2);
    }

    @Override
    public TimeEntity findById(Integer id) {
        return timeRepository.findFirstById(id);
    }
}
