package cn.com.onlinetool;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 服务消费者
 */
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerApp
{
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        System.out.println("启动服务消费者 LoadBalancerClient");
        new SpringApplicationBuilder(EurekaConsumerApp.class).web(true).run(args);
    }
}
