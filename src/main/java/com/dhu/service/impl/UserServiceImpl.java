package com.dhu.service.impl;

import com.dhu.model.UserEntity;
import com.dhu.repository.UserRepository;
import com.dhu.service.UserService;
import com.dhu.utils.Jacksons.Jacksons;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by demerzel on 2018/4/10.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserEntity saveUser(UserEntity userEntity){
        System.out.println(Jacksons.me().readAsString(userEntity));
        return userRepository.saveAndFlush(userEntity);
    }

    @Override
    public UserEntity checkLogin(String email,String password) {
        return userRepository.findFirstByEmailAndPassword(email, password);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepository.findFirstByEmail(email);
    }

    @Override
    public UserEntity findUserById(Integer id) {
        return userRepository.findFirstById(id);
    }

    @Override
    public UserEntity setMoney(UserEntity userEntity, Double money) {
        userEntity.setMoney(money);
        return userRepository.saveAndFlush(userEntity);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

}
