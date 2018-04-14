package com.dhu.repository;

import com.dhu.model.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by demerzel on 2018/4/14.
 */
@Repository
public interface HallRepository extends JpaRepository<HallEntity,Integer> {
}
