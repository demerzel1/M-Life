package com.dhu.service.impl;

import com.dhu.model.CinemaEntity;
import com.dhu.model.HallEntity;
import com.dhu.model.TimeEntity;
import com.dhu.repository.CinemaRepository;
import com.dhu.repository.HallRepository;
import com.dhu.service.CinemaService;
import com.dhu.service.TimeService;
import com.dhu.utils.Jacksons.Jacksons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by demerzel on 2018/4/12.
 */
@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    CinemaRepository cinemaRepository;

    @Autowired
    TimeService timeService;

    @Autowired
    HallRepository hallRepository;

    @Override
    public List<CinemaEntity> findAllCinema(){
        return cinemaRepository.findAll();
    }

    @Override
    public List<CinemaEntity> findCinemaByCity(Integer city_id){
        return cinemaRepository.findAllByCityId(city_id);
    }

    @Override
    public CinemaEntity findById(Integer id) {
        return cinemaRepository.findFirstById(id);
    }

    @Override
    public List<CinemaEntity> findByMovieAndDateAndCity(Integer movie_id, Date date, Integer city_id) {
        List<TimeEntity> listTime=timeService.findByMovieAndDate(movie_id,date);
        System.out.println(Jacksons.me().readAsString(listTime));
        Set<Integer> set=new HashSet<>();
        for (TimeEntity t : listTime) {
            set.add(t.getHallId());
        }
        Set<Integer> setCinema=new HashSet<>();

        for(Integer t:set){
            HallEntity hallEntity=hallRepository.findFirstById(t);
            setCinema.add(hallEntity.getCinemaId());
        }
        System.out.println(Jacksons.me().readAsString(setCinema));
        if(setCinema.size()==0)
            return null;
        List<CinemaEntity> cinemaEntityList= cinemaRepository.findAllByIdInAndCityId(setCinema,city_id);
        System.out.println(Jacksons.me().readAsString(cinemaEntityList));
        return cinemaEntityList;
    }
}
