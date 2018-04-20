package com.dhu.service;

import com.dhu.model.MovieEntity;
import com.dhu.model.SeatEntity;
import com.dhu.model.TimeEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/14.
 */
@Service
public interface TimeService {
    List<Object> findByMidCidDate(Integer mid, Integer cid, Date date);

    TimeEntity findById(Integer id);

    List<SeatEntity> findSeatById(Integer id);

    List findByCidAndDate(Integer cinema_id, Date date);

    List<TimeEntity> findByMovieAndDate(Integer movie_id, Date date);

    Map findMovieAndCinemaById(Integer id);

    TimeEntity addTime(TimeEntity timeEntity);

    List<TimeEntity> autoAddByDateMoiveHall(Date date, Integer movieId, Integer hallId,Double cost);

    Boolean checkCanAuto(Date date,Integer hallId);

    MovieEntity findMovieById(Integer id);
}
