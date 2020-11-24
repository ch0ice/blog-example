package cn.com.onlinetool.feign;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author choice
 * @date 2018/11/30 上午11:07
 *
 * @description:
 * 解决以下异常：
 * org.springframework.beans.factory.BeanCreationNotAllowedException:
 *  Error creating bean with name 'eurekaAutoServiceRegistration':
 *  Singleton bean creation not allowed while singletons of this factory are in destruction
 *  (Do not request a bean from a BeanFactory in a destroy method implementation!)
 *
 *
 * 问题是EurekaAutoServiceRegistration bean 已经被销毁，那么它收到ContextClosedEvent的ApplicationContext与FeignClients相关。
 * BeanFactory尝试再次创建该bean并触发该异常。
 *
 * 以下是整个步骤：
 * SpringApplication级别ApplicationContext关闭
 * EurekaAutoServiceRegistration.onApplicationEvent 得到调用并取消注册Eureka实例。
 * EurekaAutoServiceRegistration bean 已经被销毁（由于InetUtils和EurekaServiceRegistry被破坏）
 * 销毁FeignContext。
 * FeignContext将已经被销毁的ApplicationContext与每个FeignClient相关联。
 * ApplicationContextEurekaAutoServiceRegistration.onApplicationEvent由于EventListener注释，尝试通知。
 * 但是那个bean被破坏了，所以它试图重新创建它。
 * 现在不是创建Bean（shutdown）的时候，抛出异常
 *
 *
 * 详细查看：https://github.com/spring-cloud/spring-cloud-netflix/issues/1952
 *
 */
@Component
public class FeignBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (containsBeanDefinition(beanFactory, "feignContext", "eurekaAutoServiceRegistration")) {
            BeanDefinition bd = beanFactory.getBeanDefinition("feignContext");
            bd.setDependsOn("eurekaAutoServiceRegistration");
        }
    }

    private boolean containsBeanDefinition(ConfigurableListableBeanFactory beanFactory, String... beans) {
        return Arrays.stream(beans).allMatch(b -> beanFactory.containsBeanDefinition(b));
    }
}
