package com.dhu.controller;

import com.dhu.model.ResponseData;
import com.dhu.utils.Jacksons.Jacksons;
import com.dhu.utils.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * Created by demerzel on 2018/4/17.
 */
@RestController
@CrossOrigin
@RequestMapping("/pic")
public class PictureController {
    private ResultGenerator resultGenerator;

    @Autowired
    public PictureController(ResultGenerator resultGenerator) {
        this.resultGenerator = resultGenerator;
    }

    @RequestMapping(value = "/uploadPost")
    public ResponseData uploadPost(@RequestParam(value = "img") MultipartFile img, HttpServletRequest request) throws Exception{
        //获得物理路径webapp所在路径
       // System.out.println(Jacksons.me().readAsString(img));
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path="";
        if(!img.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            System.out.println(uuid);
            //获得文件类型
            String contentType=img.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            String allowSuffixs = "gif,jpg,jpeg,bmp,png,ico";
            if(allowSuffixs.indexOf(imageName) == -1){
                return resultGenerator.getFailResult("不是图片");
            }
            path="/static/images/posts/"+uuid+"."+imageName;
            img.transferTo(new File(pathRoot+path));
        }
        System.out.println(path);
        return resultGenerator.getSuccessResult("success",path);
    }


    @RequestMapping(value = "/uploadAvatar",method = RequestMethod.POST)
    public ResponseData uploadAvatar(@RequestParam(value = "img") MultipartFile img, HttpServletRequest request) throws Exception{
        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("");
        String path="";
        if(!img.isEmpty()){
            //生成uuid作为文件名称
            String uuid = UUID.randomUUID().toString().replaceAll("-","");
            System.out.println(uuid);
            //获得文件类型
            String contentType=img.getContentType();
            //获得文件后缀名称
            String imageName=contentType.substring(contentType.indexOf("/")+1);
            String allowSuffixs = "gif,jpg,jpeg,bmp,png,ico";
            if(allowSuffixs.indexOf(imageName) == -1){
                return resultGenerator.getFailResult("不是图片");
            }
            path="/static/images/avatars/"+uuid+"."+imageName;
            img.transferTo(new File(pathRoot+path));
        }
        System.out.println(path);
        return resultGenerator.getSuccessResult("success",path);
    }
}
