package com.tencent.wxcloudrun.service.impl;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.Optional;

@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   // 2.开启定时任务
public class SaticScheduleTask {
    @Autowired
    CounterService counterService;

    //3.添加定时任务
    @Scheduled(cron = "0/5 * * * * ?")
    //或直接指定时间间隔，例如：5秒
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {

        Optional<Counter> curCounter = counterService.getCounter(1);

        Integer count = 1;
        if (curCounter.isPresent()) {
            count += curCounter.get().getCount();
        }
        Counter counter = new Counter();
        counter.setId(1);
        counter.setCount(count);
        counterService.upsertCount(counter);

        System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
    }
}
