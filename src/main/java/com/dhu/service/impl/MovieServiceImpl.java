package com.dhu.service.impl;

import com.dhu.model.MovieEntity;
import com.dhu.repository.MovieRepository;
import com.dhu.service.MovieService;
import com.mchange.util.MEnumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/12.
 */
@Service
public class MovieServiceImpl implements MovieService {
   @Autowired
    MovieRepository movieRepository;

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
}
