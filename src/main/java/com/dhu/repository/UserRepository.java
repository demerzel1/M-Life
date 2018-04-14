package com.dhu.repository;

import com.dhu.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by demerzel on 2018/3/31.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findFirstByNameAndPassword(String name,String password);
    UserEntity findFirstByEmailAndPassword(String email,String password);
}
