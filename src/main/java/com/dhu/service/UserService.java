package com.dhu.service;

import com.dhu.model.UserEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by demerzel on 2018/4/8.
 */
@Service
public interface UserService {
    //保存用户
    UserEntity saveUser(UserEntity userEntity);

    //检查是否登陆成功
    UserEntity checkLogin(String name,String password);

    //从E-mail获取用户
    UserEntity findUserByEmail(String email);

    //从ID获取用户
    UserEntity findUserById(Integer id);

    //设置用户的余额
    UserEntity setMoney(UserEntity userEntity,Double money);

    //获取全部用户列表
    List<UserEntity> getAll();

    //删除用户
    Boolean deleteById(Integer id);

    //对名称，邮箱和手机进行模糊搜索（根据type决定）
    List<UserEntity> findByString(String str,Integer type);
}

