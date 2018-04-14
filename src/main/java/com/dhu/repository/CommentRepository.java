package com.dhu.repository;

import com.dhu.model.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import sun.misc.CEFormatException;

import java.util.List;

/**
 * Created by demerzel on 2018/4/14.
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity,Integer> {
    @Query(value="select avatar,user.name,content from user,comment,movie where movie.id=comment.movie_id and user.id=comment.user_id and movie.id=?1",nativeQuery = true)
    List<Object> findByMovie(Integer mid);
}