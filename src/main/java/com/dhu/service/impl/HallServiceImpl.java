package com.dhu.service.impl;

import com.dhu.model.HallEntity;
import com.dhu.repository.HallRepository;
import com.dhu.service.HallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by demerzel on 2018/4/20.
 */
@Service
public class HallServiceImpl implements HallService {

    @Autowired
    HallRepository hallRepository;

    @Override
    public HallEntity add(HallEntity hallEntity) {
        return hallRepository.saveAndFlush(hallEntity);
    }

    @Override
    public List<HallEntity> findByCinemaId(Integer cinemaId) {
        return hallRepository.findAllByCinemaId(cinemaId);
    }
}
