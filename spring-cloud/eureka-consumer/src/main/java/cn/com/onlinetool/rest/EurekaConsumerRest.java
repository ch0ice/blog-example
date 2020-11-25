package cn.com.onlinetool.rest;

import cn.com.onlinetool.service.EurekaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author choice
 * @description:
 * @date 2018/11/29 下午5:13
 *
 */
@RestController
@RequestMapping("/test/eureka")
public class EurekaConsumerRest {
    @Autowired
    EurekaConsumerService eurekaConsumerService;

    @GetMapping("/consumer")
    public String getServiceList(){
        return eurekaConsumerService.getServiceList();
    }
}
