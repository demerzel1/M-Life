package com.dhu.repository;

import com.dhu.model.CinemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by demerzel on 2018/4/12.
 */
@Repository
public interface CinemaRepository extends JpaRepository<CinemaEntity,Integer> {
    List<CinemaEntity> findAllByCityId(Integer city_id);
    CinemaEntity findFirstById(Integer id);
    List<CinemaEntity> findAllByIdInAndCityId(Set set,Integer city_id);
}
