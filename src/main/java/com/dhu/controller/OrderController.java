package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.model.UserEntity;
import com.dhu.service.OrderService;
import com.dhu.service.UserService;
import com.dhu.utils.Jacksons.Jacksons;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by demerzel on 2018/4/16.
 */
@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    private ResultGenerator resultGenerator;

    private UserService userService;

    @Autowired
    public OrderController(OrderService orderService, ResultGenerator resultGenerator, UserService userService) {
        this.orderService = orderService;
        this.resultGenerator = resultGenerator;
        this.userService = userService;
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseData addOrder(@RequestBody Map map){
        Integer time_id=Integer.valueOf(map.get("tid").toString());
        Integer cnt=Integer.valueOf(map.get("cnt").toString());
        Double total_money=Double.valueOf(map.get("money").toString());
        Integer user_id=Integer.valueOf(map.get("uid").toString());
        UserEntity userEntity=userService.findUserById(user_id);
        if(userEntity.getMoney()<total_money){
            return resultGenerator.getFailResult("余额不足");
        }
        //System.out.println(111);
        List<Object> lst= (List<Object>) map.get("seat");
        System.out.println(Jacksons.me().readAsString(lst));
        int sz=lst.size();

        //检查重复元素
        Map<List<Integer>,Integer> checkMap=new HashMap<List<Integer>, Integer>();

        for (Object aLst : lst) {
            Map t = (Map) aLst;
            Integer row=Integer.valueOf(t.get("row").toString());
            Integer col=Integer.valueOf(t.get("col").toString());
            List<Integer> list = new ArrayList<>();
            list.add(row);
            list.add(col);
            if(checkMap.get(list)!=null){
                return resultGenerator.getFailResult("重复元素");
            }
            checkMap.put(list,1);
            if(orderService.checkSaled(time_id, row, col)){
                return resultGenerator.getFailResult("所选座位已被购买");
            }
        }
        //begin to order
        ResponseData responseData=resultGenerator.getSuccessResult();
        Integer i=0;
        for (Object aLst : lst) {
            i++;
            Map t = (Map) aLst;
            Integer row=Integer.valueOf(t.get("row").toString());
            Integer col=Integer.valueOf(t.get("col").toString());
            if(orderService.checkSaled(time_id, row, col)){
                return resultGenerator.getFailResult("所选座位已被购买");
            }
            responseData.putDataValue(i.toString(),orderService.addOrder(time_id,row,col,user_id));
        }
        return responseData;
    }
}
