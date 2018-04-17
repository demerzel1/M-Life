package com.dhu.service;

import com.dhu.model.MovieEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by demerzel on 2018/4/12.
 */
@Service
public interface MovieService {
    List<MovieEntity> findAllMovie();
    MovieEntity findMovieByName(String name);
    MovieEntity findMovieById(Integer id);
    MovieEntity updateMovie(MovieEntity movieEntity);
    Boolean deleteMovieById(Integer id);
    MovieEntity addMovie(MovieEntity movieEntity);
    List<MovieEntity> findAllMovieByDate(Date date);
}
