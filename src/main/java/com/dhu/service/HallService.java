package com.dhu.service;

import com.dhu.model.HallEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by demerzel on 2018/4/20.
 */
@Service
public interface HallService {
    HallEntity add(HallEntity hallEntity);
    List<HallEntity> findByCinemaId(Integer cinemaId);
}
