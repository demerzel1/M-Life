package com.dhu.service.impl;

import com.dhu.model.MovieEntity;
import com.dhu.model.OrderEntity;
import com.dhu.model.TimeEntity;
import com.dhu.repository.MovieRepository;
import com.dhu.repository.OrderRepository;
import com.dhu.service.MovieService;
import com.mchange.util.MEnumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.tools.tree.OrExpression;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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
        movieRepository.delete(movieEntity);
        movieRepository.flush();
        if(movieRepository.findFirstById(id)==null)
            return true;
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
    public List<MovieEntity> findByUserId(Integer user_id) {
        List<OrderEntity> lstOrder=orderRepository.findAllByUserId(user_id);
        Set<Integer> setTime=new HashSet<>();
        for(OrderEntity orderEntity:lstOrder){
            setTime.add(orderEntity.getTimeId());
        }

        //TODO
        return null;
    }
}
