package com.dhu.service.impl;

import com.dhu.model.*;
import com.dhu.repository.*;
import com.dhu.service.MovieService;
import com.dhu.service.TimeService;
import com.dhu.utils.CommonUtils;
import com.dhu.utils.Jacksons.Jacksons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private CinemaRepository cinemaRepository;

    @Autowired
    public TimeServiceImpl(TimeRepository timeRepository, OrderRepository orderRepository, SeatRepository seatRepository, MovieService movieService, HallRepository hallRepository, CinemaRepository cinemaRepository) {
        this.timeRepository = timeRepository;
        this.orderRepository = orderRepository;
        this.seatRepository = seatRepository;
        this.movieService = movieService;
        this.hallRepository = hallRepository;
        this.cinemaRepository = cinemaRepository;
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


        for(int i=0;i<lstMovie.size();++i) {
            Map<String,Object> map=new HashMap<>();
            Integer movie_id = lstMovie.get(i).getId();
            map.put("mid",movie_id);
            map.put("name",lstMovie.get(i).getName());
            List<TimeEntity> listTime = timeRepository.findByMovieIdAndHallIdInAndStartTimeGreaterThanEqualAndStartTimeLessThan(movie_id, lstHallId, date, date1);
            System.out.println(Jacksons.me().readAsString(listTime));
            map.put("timelist",listTime);
            if(listTime.size()>0)
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
    public Map findMovieAndCinemaById(Integer id) {
        Map<String,Object> map=new HashMap<>();
        TimeEntity timeEntity=timeRepository.findFirstById(id);
        MovieEntity movieEntity= movieService.findMovieById(timeEntity.getMovieId());
        Integer hallId=timeEntity.getHallId();
        HallEntity hallEntity=hallRepository.findFirstById(hallId);
        CinemaEntity cinemaEntity=cinemaRepository.findFirstById(hallEntity.getCinemaId());
        Double cost=timeEntity.getCost();
        map.put("cinema",cinemaEntity);
        map.put("movie",movieEntity);
        map.put("hallNumber",hallEntity.getNumber());
        map.put("startTime",timeEntity.getStartTime());
        map.put("cost",cost);
        return map;
    }



    @Override
    public TimeEntity addTime(TimeEntity timeEntity) {
        return timeRepository.saveAndFlush(timeEntity);
    }

    @Override
    public List<TimeEntity> autoAddByDateMoiveHall(Date date, Integer movieId, Integer hallId,Double cost) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr="";
        try {
            dateStr = sdf.format(date);
            System.out.println(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String timestr1=dateStr+" 08:30:00";
        String timestr2=dateStr+" 12:30:00";
        String timestr3=dateStr+" 18:30:00";
        Timestamp timestamp1=null,timestamp2=null,timestamp3=null;

        try {
            timestamp1 = Timestamp.valueOf(timestr1);
            System.out.println(timestamp1.toString());
            timestamp2=Timestamp.valueOf(timestr2);
            timestamp3=Timestamp.valueOf(timestr3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MovieEntity movieEntity=movieService.findMovieById(movieId);
        if(movieEntity==null)
            return null;
        Integer length=movieEntity.getDuration();
        Timestamp endTime1=new Timestamp((long)timestamp1.getTime()+(long)length*60*1000);
        System.out.println(endTime1.toString());
        Timestamp endTime2=new Timestamp((long)timestamp2.getTime()+(long)length*60*1000);
        Timestamp endTime3=new Timestamp((long)timestamp3.getTime()+(long)length*60*1000);
        System.out.println(Jacksons.me().readAsString(timeRepository.findAllByHallIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(hallId,timestamp1,timestamp1)));
        if(timeRepository.findAllByHallIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(hallId,timestamp1,timestamp1).size()>0)
            return null;
        if(timeRepository.findAllByHallIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(hallId,timestamp2,timestamp2).size()>0)
            return null;
        if(timeRepository.findAllByHallIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(hallId,timestamp3,timestamp3).size()>0)
            return null;
        if(timeRepository.findAllByHallIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(hallId,endTime1,endTime1).size()>0)
            return null;
        if(timeRepository.findAllByHallIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(hallId,endTime2,endTime2).size()>0)
            return null;
        if(timeRepository.findAllByHallIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(hallId,endTime3,endTime3).size()>0)
            return null;
        System.out.println(111);
        TimeEntity timeEntity1=new TimeEntity();
        timeEntity1.setMovieId(movieId);
        timeEntity1.setStartTime(timestamp1);
        timeEntity1.setEndTime(endTime1);
        timeEntity1.setHallId(hallId);
        timeEntity1.setCost(cost);

        TimeEntity timeEntity2=new TimeEntity();
        timeEntity2.setMovieId(movieId);
        timeEntity2.setStartTime(timestamp2);
        timeEntity2.setEndTime(endTime2);
        timeEntity2.setHallId(hallId);
        timeEntity2.setCost(cost);

        TimeEntity timeEntity3=new TimeEntity();
        timeEntity3.setMovieId(movieId);
        timeEntity3.setStartTime(timestamp3);
        timeEntity3.setEndTime(endTime3);
        timeEntity3.setHallId(hallId);
        timeEntity3.setCost(cost);

        addTime(timeEntity1);
        addTime(timeEntity2);
        addTime(timeEntity3);

        List<TimeEntity> list=new ArrayList<>();
        list.add(timeEntity1);
        list.add(timeEntity2);
        list.add(timeEntity3);

        return list;
    }

    @Override
    public Boolean checkCanAuto(Date date, Integer hallId) {
        Date date1=CommonUtils.me().getNextDay(date);
        if(timeRepository.findAllByHallIdAndStartTimeGreaterThanAndStartTimeLessThanEqual(hallId,date,date1).size()>0)
            return false;
        return true;
    }

    @Override
    public MovieEntity findMovieById(Integer id) {
        TimeEntity timeEntity=timeRepository.findFirstById(id);
        MovieEntity movieEntity= movieService.findMovieById(timeEntity.getMovieId());
        return movieEntity;
    }

    @Override
    public TimeEntity manualAddTime(Timestamp beginTime, Timestamp endTime, Integer movieId, Integer hallId, Double cost) {
        if(timeRepository.findAllByHallIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(hallId,beginTime,beginTime).size()>0)
            return null;
        if(timeRepository.findAllByHallIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(hallId,endTime,endTime).size()>0)
            return null;
        TimeEntity timeEntity=new TimeEntity();
        timeEntity.setStartTime(beginTime);
        timeEntity.setEndTime(endTime);
        timeEntity.setHallId(hallId);
        timeEntity.setMovieId(movieId);
        timeEntity.setCost(cost);
        return timeRepository.saveAndFlush(timeEntity);
    }


}
