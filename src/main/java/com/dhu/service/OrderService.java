package com.dhu.service;

import com.dhu.model.OrderEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

/**
 * Created by demerzel on 2018/4/16.
 */
@Service
public interface OrderService {
    OrderEntity addOrder(Integer tid,Integer row,Integer col,Integer user_id);
    Boolean checkSaled(Integer tid, Integer row,Integer col);
    List<Object> findSeatByTime(Integer time_id);
}
