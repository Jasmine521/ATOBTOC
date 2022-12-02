package com.tencent.wxcloudrun.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.feign.CourseClient;
import com.tencent.wxcloudrun.model.Token;
import com.tencent.wxcloudrun.service.CounterService;
import com.tencent.wxcloudrun.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Autowired
    CounterService counterService;


    @Autowired
    final TokenService tokenService;
    @Autowired
    private CourseClient feigonService;

    public SaticScheduleTask(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    //3.添加定时任务
//    或直接指定时间间隔，例如：5秒
    @Scheduled(cron = "0 0 0 * * ?")
    private void configureTasks() {

        String acces = feigonService.openidget("jimo");
        final String s = acces.split("\'")[3];
        final String courseId = feigonService.courseurlget(s).split("\"")[9];
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("course",courseId);
        jsonObject.put("nid","");
        jsonObject.put("cardNo","ybb");
        List<Token> token = tokenService.getToken(1);
        List<String > rs = new ArrayList<>();
        token.forEach(e->{
            jsonObject.put("nid", e.getPid());
            jsonObject.put("cardNo",e.getName());
            rs.add(feigonService.joincourse(feigonService.openidget(e.getQcshopenid()).split("\'")[3], jsonObject.toJSONString()));
        });

        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
