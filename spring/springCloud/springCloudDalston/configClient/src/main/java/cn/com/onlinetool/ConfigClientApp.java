package cn.com.onlinetool;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ConfigClientApp {

    public static void main(String[] args) {
        System.out.println("启动配置中心客户端");
        new SpringApplicationBuilder(ConfigClientApp.class).web(true).run(args);
    }

}
