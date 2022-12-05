package com.tencent.wxcloudrun.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tencent.wxcloudrun.feign.CourseClient;
import com.tencent.wxcloudrun.model.Token;
import com.tencent.wxcloudrun.service.TokenService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.CounterRequest;
import com.tencent.wxcloudrun.model.Counter;
import com.tencent.wxcloudrun.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * counter控制器
 */
@RestController
public class CounterController {

  final CounterService counterService;
  final Logger logger;

  final TokenService tokenService;
  @Autowired
  private CourseClient feigonService;

  public CounterController(@Autowired CounterService counterService, TokenService tokenService) {
    this.counterService = counterService;
    this.tokenService = tokenService;
    this.logger = LoggerFactory.getLogger(CounterController.class);
  }


  /**
   * 获取当前计数
   * @return API response json
   */
  @ApiOperation("count")
  @GetMapping(value = "/api/count")
  ApiResponse get() {
    logger.info("/api/count get request");
    Optional<Counter> counter = counterService.getCounter(1);
    Integer count = 0;
    if (counter.isPresent()) {
      count = counter.get().getCount();
    }

    return ApiResponse.ok(count);
  }


  /**
   * 更新计数，自增或者清零
   * @param request {@link CounterRequest}
   * @return API response json
   */
  @ApiOperation("count1")
  @PostMapping(value = "/api/count")
  ApiResponse create(@RequestBody CounterRequest request) {
    logger.info("/api/count post request, action: {}", request.getAction());

    Optional<Counter> curCounter = counterService.getCounter(1);
    if (request.getAction().equals("inc")) {
      Integer count = 1;
      if (curCounter.isPresent()) {
        count += curCounter.get().getCount();
      }
      Counter counter = new Counter();
      counter.setId(1);
      counter.setCount(count);
      counterService.upsertCount(counter);
      return ApiResponse.ok(count);
    } else if (request.getAction().equals("clear")) {
      if (!curCounter.isPresent()) {
        return ApiResponse.ok(0);
      }
      counterService.clearCount(1);
      return ApiResponse.ok(0);
    } else {
      return ApiResponse.error("参数action错误");
    }
  }

  /**
   * a加b
   * @param a
   * @param b
   * @return
   */
  @ApiOperation("plus")
  @GetMapping("/api/plus")
  ApiResponse plusab(@RequestParam Long a, @RequestParam Long b){
    return ApiResponse.ok(counterService.aplusb(a,b));
  }


  @ApiOperation("thiswucan")
  @GetMapping("/this")
  String thix(){
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
    return rs.toString();
  }
}