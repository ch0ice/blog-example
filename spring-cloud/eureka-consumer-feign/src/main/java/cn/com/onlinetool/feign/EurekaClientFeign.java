package cn.com.onlinetool.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author choice
 * @description:
 * @date 2018/11/29 下午8:24
 *
 */
@FeignClient("eureka-client")
public interface EurekaClientFeign {

    /**
     * 获取服务列表
     * @return
     */
    @GetMapping("/test/eureka/getServiceList")
    String getServiceList();



}
