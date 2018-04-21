package com.dhu.service;

import com.dhu.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by demerzel on 2018/4/8.
 */
@Service
public interface UserService {
    UserEntity saveUser(UserEntity userEntity);
    UserEntity checkLogin(String name,String password);
    UserEntity findUserByEmail(String email);
    UserEntity findUserById(Integer id);
    UserEntity setMoney(UserEntity userEntity,Double money);
    List<UserEntity> getAll();
    Boolean deleteById(Integer id);
    List<UserEntity> findByString(String str,Integer type);
}

