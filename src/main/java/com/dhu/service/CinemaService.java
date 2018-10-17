package com.dhu.service;

import com.dhu.model.CinemaEntity;
import com.dhu.repository.CinemaRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by demerzel on 2018/4/12.
 */
@Service
public interface CinemaService {
    List<CinemaEntity> findAllCinema();
    List<CinemaEntity> findCinemaByCity(Integer city_id);
    CinemaEntity findById(Integer id);
    List<CinemaEntity> findByMovieAndDateAndCity(Integer movie_id, Date date,Integer City_id);
}
