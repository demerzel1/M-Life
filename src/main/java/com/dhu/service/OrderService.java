package com.dhu.service;

import com.dhu.model.OrderEntity;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/16.
 */
@Service
public interface OrderService {
    OrderEntity addOrder(Integer tid,Integer row,Integer col,Integer user_id);
    Boolean checkSaled(Integer tid, Integer row,Integer col);
    List<Map> findByUserId(Integer userId);
    List<Map> findNotWatchByUserId(Integer userId);
}
