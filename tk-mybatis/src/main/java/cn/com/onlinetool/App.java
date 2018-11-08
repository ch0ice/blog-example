package cn.com.onlinetool;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * Hello world!
 */
@MapperScan(basePackages = "cn.com.onlinetool.mapper")
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(App.class);
        app.run();
        System.out.println("Hello World!");
    }
}
