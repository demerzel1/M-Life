package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.service.UserService;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


/**
 * Created by demerzel on 2018/4/13.
 */
@RestController
@RequestMapping("/api")
public class AuthenticateAPIController {
    @Autowired
    private UserService userService;

    @Autowired
    private ResultGenerator resultGenerator;
}
