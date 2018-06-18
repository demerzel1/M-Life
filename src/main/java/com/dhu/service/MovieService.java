package com.dhu.service;

import com.dhu.model.MovieEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.lang.model.type.IntersectionType;
import java.sql.Date;
import java.util.List;
import java.util.Map;

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
    List<MovieEntity> findWatchedByUserId(Integer user_id);
    List<MovieEntity> findNotOn();
    List<MovieEntity> addFromExcel(MultipartFile file);
    List<MovieEntity> findByStrName(String str);
    Map  findNumberOfTimesAndNumerOfWatchedByMovie(Integer movieId);
    List<Map> findNumberOfTimesAndNumerOfWatchedByDate(Date date);
    List<Map> findTopXMoney(Integer X);
    List<Map> findTopXType(Integer X);
}
