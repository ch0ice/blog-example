package cn.com.onlinetool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author choice
 * @description:
 * @date 2018/11/29 下午5:14
 *
 */
@Service
public class EurekaConsumerService {
    @Autowired
    LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 通过负载均衡的方式调用api
     * @return
     */
    public String getServiceList(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("eureka-client");
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/test/eureka/getServiceList";
        System.out.println(url);
        return restTemplate.getForObject(url, String.class);
    }
}
