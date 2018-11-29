package cn.com.onlinetool;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务生产者
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ConsulClientApp
{
    public static void main( String[] args )
    {
        System.out.println("将服务注册到consul");
        new SpringApplicationBuilder(ConsulClientApp.class).web(true).run(args);
    }

    @GetMapping("/health")
    public String healthCheck(){
        return "ok";
    }
}
