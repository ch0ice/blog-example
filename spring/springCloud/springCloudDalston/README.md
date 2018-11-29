### 版本信息更新内容

### 模块信息
#### consulClient 
服务生产者，注册服务到consul（consul自带server，直接安装即可）

#### consuleConsumer
服务消费者，基于LoadBalencerClient做负载均衡的服务消费者，消费注册到consul的服务

#### eurekaServer
注册中心

#### eurekaClient
服务提供者，注册服务到eureka

#### eurekaConsumer
基于LoadBalencerClient做负载均衡的服务消费者，消费注册到eureka的服务
##### loadBalencerClient
在Spring Cloud Commons中提供了大量的与服务治理相关的抽象接口，包括DiscoveryClient、这里我们即将介绍的LoadBalancerClient等。
对于这些接口的定义我们在上一篇介绍服务注册与发现时已经说过，Spring Cloud做这一层抽象，很好的解耦了服务治理体系，使得我们可以轻易的替换不同的服务治理设施。

#### eurekaConsumerRibbon
基于Ribbon做负载均衡的的服务消费者
##### ribbon
Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具。它是一个基于HTTP和TCP的客户端负载均衡器。
它可以通过在客户端中配置ribbonServerList来设置服务端列表去轮询访问以达到均衡负载的作用。

当Ribbon与Eureka联合使用时，ribbonServerList会被DiscoveryEnabledNIWSServerList重写，扩展成从Eureka注册中心中获取服务实例列表。
同时它也会用NIWSDiscoveryPing来取代IPing，它将职责委托给Eureka来确定服务端是否已经启动。

而当Ribbon与Consul联合使用时，ribbonServerList会被ConsulServerList来扩展成从Consul获取服务实例列表。
同时由ConsulPing来作为IPing接口的实现。

我们在使用Spring Cloud Ribbon的时候，不论是与Eureka还是Consul结合，
都会在引入Spring Cloud Eureka或Spring Cloud Consul依赖的时候通过自动化配置来加载上述所说的配置内容，所以我们可以快速在Spring Cloud中实现服务间调用的负载均衡。


#### eurekaConsumerFeign
基于Feign调用api
##### feign
Spring Cloud Feign是一套基于Netflix Feign实现的声明式服务调用客户端。
它使得编写Web服务客户端变得更加简单。
我们只需要通过创建接口并用注解来配置它既可完成对Web服务接口的绑定。
它具备可插拔的注解支持，包括Feign注解、JAX-RS注解。它也支持可插拔的编码器和解码器。
Spring Cloud Feign还扩展了对Spring MVC注解的支持，同时还整合了Ribbon和Eureka来提供均衡负载的HTTP客户端实现。
由于Feign是基于Ribbon实现的，所以它自带了客户端负载均衡功能，也可以通过Ribbon的IRule进行策略扩展。
另外，Feign还整合的Hystrix来实现服务的容错保护，在Dalston版本中，Feign的Hystrix默认是关闭的。