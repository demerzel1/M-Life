package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.UserService;
import com.dhu.utils.JWTResult;
import com.dhu.utils.JWTUtils;
import com.dhu.utils.Jacksons.Jacksons;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * Created by demerzel on 2018/4/13.
 */
@RestController
public class AuthenticateAPIController {

    @Autowired
    private ResultGenerator resultGenerator;

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    public ResponseData apiToken(@RequestHeader("Access-Token") String token){
        JWTResult jwtResult=JWTUtils.checkToken(token);
        System.out.println(Jacksons.me().readAsString(jwtResult));
        if (!jwtResult.isStatus()) {
            return resultGenerator.getFailResult(jwtResult.getMsg());
        }
       return resultGenerator.getSuccessResult(jwtResult);
    }

}
