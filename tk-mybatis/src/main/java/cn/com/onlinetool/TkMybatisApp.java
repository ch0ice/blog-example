package cn.com.onlinetool;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Hello world!
 */
@MapperScan(basePackages = "cn.com.onlinetool.mybatis.mapper")
@SpringBootApplication
public class TkMybatisApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(TkMybatisApp.class);
        app.run();
        System.out.println("Hello World!");
    }
}
