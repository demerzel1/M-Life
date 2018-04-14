package com.dhu.controller;

import com.dhu.model.UserEntity;
import com.dhu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by demerzel on 2018/3/28.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(){
        System.out.println("success");
        return "index";
    }

}
