package com.dhu.service.impl;

import com.dhu.model.UserEntity;
import com.dhu.repository.UserRepository;
import com.dhu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by demerzel on 2018/4/10.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity saveUser(UserEntity userEntity){
        return userRepository.saveAndFlush(userEntity);
    }

    @Override
    public UserEntity checkLogin(String email,String password) {
        return userRepository.findFirstByEmailAndPassword(email, password);
    }
}
