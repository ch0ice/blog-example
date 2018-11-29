
### 什么是SpringBoot
Spring Booti简化了基于 Spring的应用开发,通过少量的代码就能创建一个独立的、产品级別的 Spring应用。 
Spring Boot为 Spring平台及第三方库提供开箱即用的设置,这样你就可以有条不素地开始。多数 SpringBoot应用只需要很少的 Spring配置。
Spring Boot是由 Pivotal团队提供的全新框架,其设计目的是用来简化新 Spring应用的初始搭建以及开发过程。
特点是少量的代码和少量的配置就可以创建出完整的spring应用，用了全新的特定方式去配置框架，从而使开发人员不再需要定义样板化的配置。简约而不简单。
用我的话来理解,就是Spring Boot其实不是什么新的框架,它默认配置了很多框架的使用方式,就像 maven整合了所有的jar包，Spring Boot整合了所有的框架。
Spring Boot的核心思想就是约定大于配置,一切自动完成。
采用 Spring Boot可以大大的简化你的开发模式,所有你想集成的常用框架,它都有对应的组件支持。

### 什么是SpringCloud  
Spring Cloud是一系列框架的有序集合。
它利用 Spring Boot的开发便利性巧妙地简化了分布式系统基础设施的开发,如服务发现注册、配置中心、消息总线线、负载均衠、断路器、数据监控等,都可以用 Spring Boot的开发风格做到一键启动和部署。 
Spring并没有重复制造轮子,它只是将目前各家公司开发的比较成熟、经得起实际考验的服务框架组台起来,通过 Spring Boot风格进行再封装屏蔽掉了复杂的配置和实现原理,最终给开发者留出了一套简单易懂、易部署和易维护的分布式系統开发工具包。
微服务是可以独立部署、水平扩展、独立访问(或者有独立的数据库)的服务单元, Spring Cloud就是这些微服务的大管家,采用了微服务这种架构之后,项目的数量会非常多, Spring Cloud做为大管家就需要提供各种方案来维护整个生态。
Spring Cloud就是一套分布式服务治理的框架,既然它是一套服务治理的框架,那么它本身不会提供具体功能性的操作,更专注于服务之间的通讯、熔断、监控等。
因此就需要很多的组件来支持一套功能。
    
### SpringBoot 与 SpringCloud 的关系
Spring Boot是 Spring的一套快速配置脚手架,可以基于 Spring Boot快速开发单个微服务。 
SpringCloud是一个基于 Spring Boot实现的云应用开发工具;
Spring Boot专注于快速、方便集成的单个微服务个体，Spring Cloud关注全局的服务治理框架; 
Spring Boot使用了默认大于配置的理念,很多集成方案已经帮你选择好了,能不配置就不配置, Spring Cloud很大的一部分是基于 Spring Boot来实现,
可以不基于 Spring Boot吗?不可以。 Spring Boot可以离开 Spring Cloud独立使用开发项目,但是 Spring Cloud离不开 Spring Boot,属于依赖的关系
Spring-> Spring Boot> Spring Cloud这样的关系
  

### SpringBoot 与 SpringCloud 的区别
1、Spring boot 是 Spring 的一套快速配置脚手架，可以基于spring boot 快速开发单个微服务；Spring Cloud是一个基于Spring Boot实现的云应用开发工具；
2、Spring boot专注于快速、方便集成的单个个体，Spring Cloud是关注全局的服务治理框架；
3、spring boot使用了默认大于配置的理念，很多集成方案已经帮你选择好了，能不配置就不配置，Spring Cloud很大的一部分是基于Spring boot来实现。
4、Spring boot可以离开Spring Cloud独立使用开发项目，但是Spring Cloud离不开Spring boot，属于依赖的关系。

### SpringBoot 与 SpringCloud 的版本对应关系
SpringBoot          SpringCloud
2.1.x               Greenwich 
2.0.x               Finchley
1.5.x               Edgware
1.5.x               Dalston

### Spring 版本说明
GA:General Availability,正式发布的版本，官方推荐使用此版本。在国外都是用GA来说明release版本的。

PRE: 预览版,内部测试版. 主要是给开发人员和测试人员测试和找BUG用的，不建议使用；

SNAPSHOT: 快照版，可以稳定使用，且仍在继续改进版本。