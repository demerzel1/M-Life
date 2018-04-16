package com.dhu.service;

import com.dhu.model.SeatEntity;
import org.springframework.stereotype.Service;

/**
 * Created by demerzel on 2018/4/16.
 */
@Service
public interface SeatService {
    SeatEntity addSeat(Integer hall,Integer row,Integer col);
}
