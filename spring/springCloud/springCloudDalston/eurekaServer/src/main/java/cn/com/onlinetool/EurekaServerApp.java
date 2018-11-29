package cn.com.onlinetool;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eureka server
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerApp
{
    public static void main(String[] args){
        System.out.println("启动eureka服务端");
        new SpringApplicationBuilder(EurekaServerApp.class).web(true).run(args);
    }
}
