package cn.com.onlinetool;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    public static void main(String[] args) {
        ApplicationContext context = new SpringApplicationBuilder(App.class)
                .run(args);
    }
}
