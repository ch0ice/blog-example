package cn.com.onlinetool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@MapperScan(basePackages = "cn.com.onlinetool.mapper")
@SpringBootApplication
public class MybatisApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MybatisApp.class);
        app.run();
        System.out.println("Hello World!");
    }
}
