package cn.com.onlinetool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableCaching
public class App {
    public static void main( String[] args ) {
        SpringApplication.run(App.class,args);
//        ApplicationContext context =  new ClassPathXmlApplicationContext("application.properties");
//        @SuppressWarnings("unchecked")
//        RedisTemplate redisTemplate = (RedisTemplate)context.getBean("redisTemplate");
    }
}
