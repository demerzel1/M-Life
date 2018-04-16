package com.dhu.repository;

import com.dhu.model.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by demerzel on 2018/4/12.
 */
@Repository
public interface MovieRepository extends JpaRepository<MovieEntity,Integer> {
    MovieEntity findFirstById(Integer Id);
    MovieEntity findFirstByName(String Name);
    MovieEntity findFirstByEnglishname(String Englishname);
}
