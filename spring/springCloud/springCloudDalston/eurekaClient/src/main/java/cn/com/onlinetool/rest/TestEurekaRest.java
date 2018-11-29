package cn.com.onlinetool.rest;

import cn.com.onlinetool.service.TestEurekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author choice
 * @description:
 * @date 2018/11/29 下午12:50
 *
 */
@RequestMapping("/test/eureka")
@RestController
public class TestEurekaRest {
    @Autowired
    TestEurekaService testEurekaService;

    @GetMapping("/getServiceList")
    public String dc(){
        return testEurekaService.getServiceList();

    }
}
