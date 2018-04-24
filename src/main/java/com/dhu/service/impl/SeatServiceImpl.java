package com.dhu.service.impl;

import com.dhu.model.SeatEntity;
import com.dhu.repository.SeatRepository;
import com.dhu.service.SeatService;
import com.dhu.utils.Jacksons.Jacksons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by demerzel on 2018/4/16.
 */
@Service
@Transactional
public class SeatServiceImpl implements SeatService {
    @Autowired
    SeatRepository seatRepository;

    Jacksons jacksons= Jacksons.me();
    @Override
    public SeatEntity addSeat(Integer hall, Integer row, Integer col) {
        SeatEntity seatEntity =new SeatEntity();
        seatEntity.setRow(row);
        seatEntity.setCol(col);
        seatEntity.setHallId(hall);
        System.out.println(jacksons.readAsString(seatEntity));


        return seatRepository.saveAndFlush(seatEntity);
    }

    @Override
    @Transactional
    public SeatEntity findSeat(Integer hall, Integer row, Integer col) {
        seatRepository.flush();
        return seatRepository.findFirstByHallIdAndRowAndCol(hall,row,col);
    }
}
