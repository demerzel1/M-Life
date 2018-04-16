package com.dhu.service.impl;

import com.dhu.model.CinemaEntity;
import com.dhu.repository.CinemaRepository;
import com.dhu.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by demerzel on 2018/4/12.
 */
@Service
public class CinemaServiceImpl implements CinemaService {
    @Autowired
    CinemaRepository cinemaRepository;

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
}
