package cn.com.onlinetool.rest;

import cn.com.onlinetool.service.EurekaConsumerRibbonService;
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
public class EurekaConsumerRibbonRest {
    @Autowired
    EurekaConsumerRibbonService eurekaConsumerRibbonService;

    @GetMapping("/ribbonConsumer")
    public String getServiceList(){
        return eurekaConsumerRibbonService.getServiceList();
    }
}
