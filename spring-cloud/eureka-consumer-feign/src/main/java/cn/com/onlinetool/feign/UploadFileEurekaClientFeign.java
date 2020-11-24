package cn.com.onlinetool.feign;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;



/**
 * @author choice
 * @description:
 * @date 2018/11/29 下午8:46
 *
 */
@FeignClient(value = "eureka-client", configuration = UploadFileEurekaClientFeign.MultipartSupportConfig.class)
public interface UploadFileEurekaClientFeign {

    /**
     * 测试上传文件
     * @param file
     * @return
     */
    @PostMapping(value = "/test/eureka/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadFile(@RequestPart(value = "file") MultipartFile file);


    @Configuration
    class MultipartSupportConfig {
        @Bean
        public Encoder feignFormEncoder() {
            return new SpringFormEncoder();
        }
    }
}
