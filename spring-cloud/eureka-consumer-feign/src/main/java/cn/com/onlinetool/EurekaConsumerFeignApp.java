package cn.com.onlinetool;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class EurekaConsumerFeignApp
{
    public static void main(String[] args) {
        System.out.println("启动服务消费者 feign");
        new SpringApplicationBuilder(EurekaConsumerFeignApp.class).web(true).run(args);
    }
}
