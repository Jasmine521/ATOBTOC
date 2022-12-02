package com.tencent.wxcloudrun.feign;

import org.bouncycastle.pqc.math.linearalgebra.PolynomialRingGF2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name = "qcsh",url = "https://qcsh.h5yunban.com")
public interface CourseClient {

    @GetMapping(value = "${openurl}")
    String openidget(@RequestParam(name = "openid") String openid);

    @GetMapping(value = "/youth-learning/cgi-bin/common-api/course/current",consumes = "application/x-www-form-urlencoded;charset=UTF-8")
    String courseurlget(@RequestParam(name = "accessToken") String accessToken);

    @PostMapping(value = "${joinurl}")
    String joincourse(@RequestParam(name = "accessToken") String accessToken, @RequestBody String data);
}
