package com.dhu.service.impl;

import com.dhu.model.OrderEntity;
import com.dhu.model.SeatEntity;
import com.dhu.model.TimeEntity;
import com.dhu.model.UserEntity;
import com.dhu.repository.OrderRepository;
import com.dhu.service.OrderService;
import com.dhu.service.SeatService;
import com.dhu.service.TimeService;
import com.dhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/16.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TimeService timeService;

    @Autowired
    SeatService seatService;

    @Autowired
    UserService userService;

    @Override
    public OrderEntity addOrder(Integer tid, Integer row, Integer col, Integer user_id) {
        TimeEntity timeEntity=timeService.findById(tid);
        Date date=new Date(timeEntity.getStartTime().getTime());
        Double cost=timeEntity.getCost();
        Integer hall_id=timeEntity.getHallId();
        SeatEntity seatEntity=seatService.findSeat(hall_id,row,col);
        Integer seat_id=seatEntity.getId();
        Timestamp order_time=new Timestamp(System.currentTimeMillis());

        UserEntity userEntity=userService.findUserById(user_id);
        userService.setMoney(userEntity,userEntity.getMoney()-cost);


        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setOrderTime(order_time);
        orderEntity.setCost(cost);
        orderEntity.setSeatId(seat_id);
        orderEntity.setTimeId(tid);
        orderEntity.setWatchTime(date);
        orderEntity.setUserId(user_id);
        return orderRepository.saveAndFlush(orderEntity);
    }

    @Override
    public Boolean checkSaled(Integer tid, Integer row, Integer col) {
        TimeEntity timeEntity=timeService.findById(tid);
        Integer hall_id=timeEntity.getHallId();
        SeatEntity seatEntity=seatService.findSeat(hall_id,row,col);
        Integer seat_id=seatEntity.getId();
        if(orderRepository.findFirstByTimeIdAndSeatId(tid,seat_id)==null)
            return false;
        return true;
    }

    @Override
    public List<Object> findSeatByTime(Integer time_id) {
        //TODO
        return null;
    }
}
