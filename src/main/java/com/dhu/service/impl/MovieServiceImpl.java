package com.dhu.service.impl;

import com.dhu.model.MovieEntity;
import com.dhu.model.OrderEntity;
import com.dhu.model.TimeEntity;
import com.dhu.repository.MovieRepository;
import com.dhu.repository.OrderRepository;
import com.dhu.repository.TimeRepository;
import com.dhu.service.MovieService;
import com.dhu.utils.CommonUtils;
import com.dhu.utils.ExcelUtils;
import com.dhu.utils.Jacksons.Jacksons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by demerzel on 2018/4/12.
 */
@Service
public class MovieServiceImpl implements MovieService {
   @Autowired
    MovieRepository movieRepository;

   @Autowired
    OrderRepository orderRepository;

   @Autowired
    TimeRepository timeRepository;

    @Override
    public List<MovieEntity> findAllMovie(){
        return movieRepository.findAll();
    }
    @Override
    public MovieEntity findMovieByName(String name){
        return movieRepository.findFirstByName(name);
    }

    @Override
    public MovieEntity findMovieById(Integer id){
        return movieRepository.findFirstById(id);
    }

    @Override
    public MovieEntity updateMovie(MovieEntity movieEntity) {
        return movieRepository.saveAndFlush(movieEntity);
    }

    @Override
    public Boolean deleteMovieById(Integer id) {
        MovieEntity movieEntity=movieRepository.findFirstById(id);
        System.out.println(Jacksons.me().readAsString(movieEntity));
        movieRepository.delete(movieEntity);
        movieRepository.flush();
        if(movieRepository.findFirstById(id)==null){
            System.out.println(3333);
            return true;
        }
        System.out.println(2222);
        return false;
    }

    @Override
    public MovieEntity addMovie(MovieEntity movieEntity) {
        return movieRepository.saveAndFlush(movieEntity);
    }

    @Override
    public List<MovieEntity> findAllMovieByDate(Date date) {
        return movieRepository.findAllByBeginTimeLessThanEqualAndEndTimeGreaterThanEqual(date,date);
    }

    @Override
    public List<MovieEntity> findWatchedByUserId(Integer user_id) {
        List<OrderEntity> lstOrder=orderRepository.findAllByUserId(user_id);
        Set<Integer> setTime=new HashSet<>();
        for(OrderEntity orderEntity:lstOrder){
            setTime.add(orderEntity.getTimeId());
        }
        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        List<TimeEntity> timeEntityList=timeRepository.findAllByIdInAndStartTimeLessThan(setTime,timestamp);
        Set<Integer> movieSet=new HashSet<>();
        for(TimeEntity timeEntity:timeEntityList){
            movieSet.add(timeEntity.getMovieId());
        }
        List<MovieEntity> movieEntityList=movieRepository.findAllByIdIn(movieSet);
        return movieEntityList;
    }

    @Override
    public List<MovieEntity> findNotOn() {
        Date date=new Date(System.currentTimeMillis());
        return movieRepository.findAllByBeginTimeGreaterThan(date);
    }

    @Override
    public List<MovieEntity> addFromExcel(MultipartFile file) {
        ExcelUtils excelUtils=new ExcelUtils();
        List<MovieEntity> movieEntityList=excelUtils.getExcelInfo(file);
        System.out.println(Jacksons.me().readAsString(movieEntityList));
        for(MovieEntity movieEntity:movieEntityList){
            movieRepository.saveAndFlush(movieEntity);
        }
        return movieEntityList;
    }
}
