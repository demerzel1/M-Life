package com.dhu.repository;

import com.dhu.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by demerzel on 2018/4/12.
 */
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {
    MovieEntity findFirstById(Integer Id);
    MovieEntity findFirstByName(String Name);
    MovieEntity findFirstByEnglishname(String Englishname);
    List<MovieEntity> findAllByBeginTimeLessThanEqualAndEndTimeGreaterThanEqual(Date date1,Date date2);
    List<MovieEntity> findAllByIdIn(Set set);
    List<MovieEntity> findAllByBeginTimeGreaterThan(Date date);
    List<MovieEntity> findAllByNameContaining(String name);
}
