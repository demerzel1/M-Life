package com.dhu.repository;

import com.dhu.model.TimeEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * Created by demerzel on 2018/4/14.
 */
@Repository
public interface TimeRepository extends JpaRepository<TimeEntity,Integer> {
    @Query(value = "select time.id,number,start_time,cost from time,hall,movie,cinema where time.movie_id=movie.id and time.hall_id=hall.id and hall.cinema_id=cinema.id and movie_id=?1 and cinema_id=?2 and ?3<=start_time and ?4>start_time",nativeQuery = true)
    List<Object> findByMCD(Integer mid, Integer cid, Date date1, Date date2);

    TimeEntity findFirstById(Integer id);

    List<TimeEntity> findByMovieIdAndHallIdInAndStartTimeGreaterThanEqualAndStartTimeLessThan(Integer movie_id,List<Integer> lst,Date date1,Date date2);

    List<TimeEntity> findAllByMovieIdAndStartTimeGreaterThanEqualAndStartTimeLessThan(Integer movie_id,Date date1,Date date2);

    List<TimeEntity> findAllByIdInAndStartTimeLessThan(Set<Integer> set, Timestamp timestamp);

    List<TimeEntity> findAllByHallIdAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(Integer hallId,Timestamp timestamp1,Timestamp timestamp2);

    List<TimeEntity> findAllByHallIdAndStartTimeGreaterThanAndStartTimeLessThanEqual(Integer hallId,Date date1,Date date2);

    List<TimeEntity> findAllByMovieId(Integer movieId);
}
