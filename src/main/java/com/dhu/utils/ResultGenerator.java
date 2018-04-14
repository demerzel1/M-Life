package com.dhu.utils;

import com.dhu.model.ResponseData;
import com.dhu.model.ResultCode;
import org.springframework.stereotype.Component;

/**
 * Created by demerzel on 2018/4/10.
 */
@Component
public class ResultGenerator {

    private static final String SUCCESS = "success";
    //成功
    public ResponseData getSuccessResult() {
        return new ResponseData(ResultCode.SUCCESS.getCode(),SUCCESS);
    }
    //成功，附带额外数据
    public  ResponseData getSuccessResult(Object data) {
        return new ResponseData(ResultCode.SUCCESS.getCode(),SUCCESS).putDataValue("data",data);
    }
    //成功，自定义消息及数据
    public  ResponseData getSuccessResult(String message,Object data) {
        return new ResponseData(ResultCode.SUCCESS.getCode(),message).putDataValue("data",data);
    }
    //失败，附带消息
    public  ResponseData getFailResult(String message) {
        return new ResponseData(ResultCode.FAIL.getCode(),message);
    }
    //失败，自定义消息及数据
    public ResponseData getFailResult(String message, Object data) {
        return new ResponseData(ResultCode.FAIL.getCode(),message).putDataValue("data",data);
    }
    //自定义创建
    public ResponseData getFreeResult(ResultCode code, String message, Object data) {
        return new ResponseData(code.getCode(),message).putDataValue("data",data);
    }
}
