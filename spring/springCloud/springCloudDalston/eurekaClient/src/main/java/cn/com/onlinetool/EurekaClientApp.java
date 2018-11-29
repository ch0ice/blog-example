package cn.com.onlinetool;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * eureka client
 */

@EnableDiscoveryClient
@SpringBootApplication
public class EurekaClientApp
{
    public static void main( String[] args )
    {
        System.out.println("启动服务注册到eureka");
        new SpringApplicationBuilder(EurekaClientApp.class).web(true).run(args);
    }
}
