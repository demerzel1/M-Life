package com.dhu.service;

import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by demerzel on 2018/4/14.
 */
@Service
public interface TimeService {
    List<Object> findByMidCidDate(Integer mid, Integer cid,Date date);
}
