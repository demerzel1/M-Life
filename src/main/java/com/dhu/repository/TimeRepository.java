package com.dhu.repository;

import com.dhu.model.TimeEntity;
import org.jboss.logging.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by demerzel on 2018/4/14.
 */
@Repository
public interface TimeRepository extends JpaRepository<TimeEntity,Integer> {
    @Query(value = "select hall.number,start_time,cost,begin_time,movie.end_time from time,hall,movie,cinema where time.movie_id=movie.id and time.hall_id=hall.id and hall.cinema_id=cinema.id and movie_id=?1 and cinema_id=?2 and ?3<=start_time and ?4>=start_time",nativeQuery = true)
    List<Object> findByMCD(Integer mid, Integer cid,Date date1,Date date2);
}
