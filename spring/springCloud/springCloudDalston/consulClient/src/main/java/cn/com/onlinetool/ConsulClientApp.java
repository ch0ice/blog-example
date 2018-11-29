package cn.com.onlinetool;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ConsulClientApp
{
    public static void main( String[] args )
    {
        System.out.println("启动consul客户端");
        new SpringApplicationBuilder(ConsulClientApp.class).web(true).run(args);
    }
}
