package com.dhu.repository;

import com.dhu.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by demerzel on 2018/3/31.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findFirstByNameAndPassword(String name,String password);
    UserEntity findFirstByEmailAndPassword(String email,String password);
    UserEntity findFirstByEmail(String email);
    UserEntity findFirstById(Integer id);
    List<UserEntity> findAllByIsAdmin(Byte isAdmin);
    List<UserEntity> findAllByNameContainingAndIsAdmin(String name,Byte isAdmin);
    List<UserEntity> findAllByEmailContainingAndIsAdmin(String email,Byte isAdmin);
    List<UserEntity> findAllByTelContainingAndIsAdmin(String tel,Byte isAdmin);
}
