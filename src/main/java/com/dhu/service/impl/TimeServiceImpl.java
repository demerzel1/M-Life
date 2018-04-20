package com.dhu.service.impl;

import com.dhu.model.*;
import com.dhu.repository.HallRepository;
import com.dhu.repository.OrderRepository;
import com.dhu.repository.SeatRepository;
import com.dhu.repository.TimeRepository;
import com.dhu.service.MovieService;
import com.dhu.service.TimeService;
import com.dhu.utils.CommonUtils;
import com.dhu.utils.Jacksons.Jacksons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/14.
 */
@Service
public class TimeServiceImpl implements TimeService {
    private CommonUtils commonUtils=new CommonUtils();

    private TimeRepository timeRepository;

    private OrderRepository orderRepository;

    private SeatRepository seatRepository;

    private MovieService movieService;

    private HallRepository hallRepository;

    @Autowired
    public TimeServiceImpl(TimeRepository timeRepository, OrderRepository orderRepository, SeatRepository seatRepository, MovieService movieService, HallRepository hallRepository) {
        this.timeRepository = timeRepository;
        this.orderRepository = orderRepository;
        this.seatRepository = seatRepository;
        this.movieService = movieService;
        this.hallRepository = hallRepository;
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

    @Override
    public List<SeatEntity> findSeatById(Integer id) {
        List<OrderEntity> list=orderRepository.findAllByTimeId(id);
        List<SeatEntity> reslist=new ArrayList<>();
        for(int i=0;i<list.size();++i){
            Integer seat_id=list.get(i).getSeatId();
            SeatEntity seatEntity=seatRepository.findFirstById(seat_id);
            reslist.add(seatEntity);
        }
        return reslist;
    }

    @Override
    public List findByCidAndDate(Integer cinema_id, Date date) {
        List<MovieEntity> lstMovie=movieService.findAllMovieByDate(date);
        List<HallEntity> lstHall=hallRepository.findAllByCinemaId(cinema_id);
        List<Integer> lstHallId=new ArrayList<>();
        for(int i=0;i<lstHall.size();++i){
            lstHallId.add(lstHall.get(i).getId());
        }
        Date date1=commonUtils.getNextDay(date);

        System.out.println(Jacksons.me().readAsString(lstMovie));

        System.out.println(Jacksons.me().readAsString(lstHallId));

        List<Map> list=new ArrayList<>();

        Map<String,Object> map=new HashMap<>();
        for(int i=0;i<lstMovie.size();++i) {
            Integer movie_id = lstMovie.get(i).getId();
            map.put("mid",movie_id);
            map.put("name",lstMovie.get(i).getName());
            List<TimeEntity> listTime = timeRepository.findByMovieIdAndHallIdInAndStartTimeGreaterThanEqualAndStartTimeLessThan(movie_id, lstHallId, date, date1);
            System.out.println(Jacksons.me().readAsString(listTime));
            map.put("timelist",listTime);
            list.add(map);
        }
        return list;
    }

    @Override
    public List<TimeEntity> findByMovieAndDate(Integer movie_id, Date date) {
        Date date1=CommonUtils.me().getNextDay(date);
        return timeRepository.findAllByMovieIdAndStartTimeGreaterThanEqualAndStartTimeLessThan(movie_id,date,date1);
    }

    @Override
    public MovieEntity findMovieById(Integer id) {
        TimeEntity timeEntity=timeRepository.findFirstById(id);
        return movieService.findMovieById(timeEntity.getMovieId());
    }

    @Override
    public TimeEntity addTime(TimeEntity timeEntity) {
        return null;
    }

    @Override
    public List<TimeEntity> autoAyDateMoiveHall(Date date, Integer movieId, Integer hallId) {
       // Timestamp timestamp=new Timestamp()
        return null;
    }


}
