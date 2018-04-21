package com.dhu.service.impl;

import com.dhu.model.*;
import com.dhu.repository.CinemaRepository;
import com.dhu.repository.HallRepository;
import com.dhu.repository.OrderRepository;
import com.dhu.repository.SeatRepository;
import com.dhu.service.OrderService;
import com.dhu.service.SeatService;
import com.dhu.service.TimeService;
import com.dhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    SeatRepository seatRepository;

    @Autowired
    HallRepository hallRepository;

    @Autowired
    CinemaRepository cinemaRepository;

    @Override
    public OrderEntity addOrder(Integer tid, Integer row, Integer col, Integer user_id) {
        TimeEntity timeEntity=timeService.findById(tid);
      //  System.out.println(timeEntity.getStartTime());
        Timestamp date=new Timestamp(timeEntity.getStartTime().getTime());
        Double cost=timeEntity.getCost();
        Integer hall_id=timeEntity.getHallId();
        SeatEntity seatEntity=seatService.findSeat(hall_id,row,col);
        Integer seat_id=seatEntity.getId();
        Timestamp order_time=new Timestamp(date.getTime()-1*24*60*1000);

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
        orderRepository.flush();
        if(orderRepository.findFirstByTimeIdAndSeatId(tid,seat_id)==null)
            return false;
        return true;
    }

    @Override
    public List<Map> findByUserId(Integer userId) {
        orderRepository.flush();
        List<OrderEntity> orderEntityList= orderRepository.findAllByUserIdOrderByOrderTimeDesc(userId);
        List<Map> list=new ArrayList<>();
        list=getFullOrder(orderEntityList, list);
        return list;
    }


    @Override
    public List<Map> findNotWatchByUserId(Integer userId) {
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        orderRepository.flush();
        List<OrderEntity> orderEntityList=  orderRepository.findAllByUserIdAndWatchTimeGreaterThanEqualOrderByOrderTimeDesc(userId,timestamp);
        List<Map> list=new ArrayList<>();
        list=getFullOrder(orderEntityList, list);
        return list;
    }

    private List<Map> getFullOrder(List<OrderEntity> orderEntityList, List<Map> list) {
        for(OrderEntity orderEntity:orderEntityList){
            seatRepository.flush();
            hallRepository.flush();
            cinemaRepository.flush();
            MovieEntity movieEntity=timeService.findMovieById(orderEntity.getTimeId());
            TimeEntity timeEntity=timeService.findById(orderEntity.getTimeId());
            SeatEntity seatEntity=seatRepository.findFirstById(orderEntity.getSeatId());
            HallEntity hallEntity=hallRepository.findFirstById(seatEntity.getHallId());
            CinemaEntity cinemaEntity=cinemaRepository.findFirstById(hallEntity.getCinemaId());
            Map<String,Object> map=new HashMap<>();
            map.put("orderId",orderEntity.getId());
            map.put("orderTime",orderEntity.getOrderTime());
            map.put("watchTime",orderEntity.getWatchTime());
            map.put("endTime",timeEntity.getEndTime());
            map.put("cost",orderEntity.getCost());
            map.put("cinema",cinemaEntity.getName());
            map.put("movieName",movieEntity.getName());
            map.put("movieEnglishName",movieEntity.getEnglishname());
            map.put("hall",hallEntity.getNumber());
            map.put("row",seatEntity.getRow());
            map.put("col",seatEntity.getCol());
            map.put("post",movieEntity.getPoster());
            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
            if(orderEntity.getWatchTime().before(timestamp)){
                map.put("watched",1);
            }else{
                map.put("watched",0);
            }
            list.add(map);
        }
        return list;
    }

}

