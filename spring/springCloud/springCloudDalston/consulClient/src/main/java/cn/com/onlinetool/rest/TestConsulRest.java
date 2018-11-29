package cn.com.onlinetool.rest;

import cn.com.onlinetool.service.TestConsulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author choice
 * @description:
 * @date 2018/11/29 下午2:42
 *
 */
@RestController
@RequestMapping("/test/consul")
public class TestConsulRest {
    @Autowired
    TestConsulService testConsulService;

    @GetMapping("/getServiceList")
    public String getServiceList(){
        return testConsulService.getServiceList();

    }


}
