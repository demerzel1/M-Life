package com.dhu.repository;

import com.dhu.model.SeatEntity;
import com.dhu.service.SeatService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by demerzel on 2018/4/16.
 */
@Repository
public interface SeatRepository extends JpaRepository<SeatEntity,Integer> {
    SeatEntity findFirstByHallIdAndRowAndCol(Integer hall_id,Integer row,Integer col);
    SeatEntity findFirstById(Integer id);
}
