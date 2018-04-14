package com.dhu.service.impl;

import com.dhu.model.MovieEntity;
import com.dhu.repository.MovieRepository;
import com.dhu.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
