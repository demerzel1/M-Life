package com.dhu.service;

import com.dhu.model.UserEntity;
import org.springframework.stereotype.Service;

/**
 * Created by demerzel on 2018/4/8.
 */
@Service
public interface UserService {
    UserEntity saveUser(UserEntity userEntity);
    UserEntity checkLogin(String name,String password);
}
