package cn.com.onlinetool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

/**
 * @author choice
 * @description:
 * @date 2018/11/29 下午12:51
 *
 */
@Service
public class TestEurekaService {
    @Autowired
    DiscoveryClient discoveryClient;

    /**
     * 获取eureka上的服务清单
     * @return
     */
    public String getServiceList(){
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }
}
