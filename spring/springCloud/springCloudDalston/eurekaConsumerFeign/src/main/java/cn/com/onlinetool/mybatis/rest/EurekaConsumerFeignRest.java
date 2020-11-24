package cn.com.onlinetool.mybatis.rest;

import cn.com.onlinetool.feign.EurekaClientFeign;
import cn.com.onlinetool.feign.UploadFileEurekaClientFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author choice
 * @description:
 * @date 2018/11/29 下午5:13
 *
 */
@RestController
@RequestMapping("/test/eureka")
public class EurekaConsumerFeignRest {
    @Autowired
    EurekaClientFeign eurekaClientFeign;
    @Autowired
    UploadFileEurekaClientFeign fileEurekaClientFeign;

    @GetMapping("/feignConsumer")
    public String getServiceList(){
        return eurekaClientFeign.getServiceList();
    }

    @PostMapping("/uploadFileFeignConsumer")
    public String getServiceList(@RequestPart("file")MultipartFile file){
        return fileEurekaClientFeign.uploadFile(file);
    }
}
