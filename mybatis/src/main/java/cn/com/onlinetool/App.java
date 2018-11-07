package cn.com.onlinetool;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@MapperScan("cn.com.onlinetool.asd")
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        SpringApplication app = new SpringApplication(App.class);
        app.run();
        System.out.println( "Hello World!" );
    }
}
