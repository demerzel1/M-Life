package com.dhu.repository;

import com.dhu.model.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by demerzel on 2018/4/12.
 */
@Repository
public interface CinemaRepository extends JpaRepository<CinemaEntity,Integer> {
}
