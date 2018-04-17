package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.model.UserEntity;
import com.dhu.service.UserService;
import com.dhu.utils.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/10.
 */
@RestController
@CrossOrigin
@RequestMapping("/user")
@Validated
public class UserController {


    private Logger log = LoggerFactory.getLogger(this.getClass());

    private UserService userService;

    private ResultGenerator resultGenerator;

    private String jsonstr;

    @Autowired
    public UserController(UserService userService, ResultGenerator resultGenerator) {
        this.userService = userService;
        this.resultGenerator = resultGenerator;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseData register(@Valid @RequestBody UserEntity userEntity) {
        UserEntity userEntity1=userService.findUserByEmail(userEntity.getEmail());
        if(userEntity1!=null){
            return resultGenerator.getFailResult("违反主键/唯一约束条件");
        }
        return resultGenerator.getSuccessResult("用户注册成功",userService.saveUser(userEntity));
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseData login(@RequestBody Map map) {
        String email=map.get("email").toString();
        String password=map.get("password").toString();
        UserEntity userEntity = userService.checkLogin(email,password);
        if(userEntity!=null){
            return resultGenerator.getSuccessResult("success",userEntity);
        }
        return resultGenerator.getFailResult("用户名/密码错误");
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseData updateUser(@RequestBody UserEntity userEntity){
        UserEntity userEntity1=userService.findUserByEmail(userEntity.getEmail());
        if(userEntity1!=null){
            return resultGenerator.getFailResult("违反主键/唯一约束条件");
        }
        return resultGenerator.getSuccessResult("用户更新成功",userService.saveUser(userEntity));
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseData handleConstraintViolationException(ConstraintViolationException cve) {
        String errorMessage = cve.getConstraintViolations().iterator().next().getMessage();
        return resultGenerator.getFailResult(errorMessage);
    }

    /**
     * 主键/唯一约束违反异常
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseData handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        //如果注册两个相同的用户名到报这个异常
        return resultGenerator.getFailResult("违反主键/唯一约束条件");
    }

}
