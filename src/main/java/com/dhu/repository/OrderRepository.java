package com.dhu.repository;

import com.dhu.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by demerzel on 2018/4/16.
 */
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {
    OrderEntity findFirstByTimeIdAndSeatId(Integer time_id,Integer seat_id);
}
