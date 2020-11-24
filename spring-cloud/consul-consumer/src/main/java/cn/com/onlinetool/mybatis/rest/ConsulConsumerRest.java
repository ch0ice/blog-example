package cn.com.onlinetool.mybatis.rest;

import cn.com.onlinetool.service.ConsulConsumerService;
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
@RequestMapping("/test/consul")
public class ConsulConsumerRest {
    @Autowired
    ConsulConsumerService consulConsumerService;

    @GetMapping("/consumer")
    public String getServiceList(){
        return consulConsumerService.getServiceList();
    }
}
