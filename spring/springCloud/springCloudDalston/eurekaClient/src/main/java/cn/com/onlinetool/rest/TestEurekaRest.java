package cn.com.onlinetool.rest;

import cn.com.onlinetool.service.TestEurekaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author choice
 * @description:
 * @date 2018/11/29 下午12:50
 *
 */
@RequestMapping("/test/eureka")
@RestController
public class TestEurekaRest {
    @Autowired
    TestEurekaService testEurekaService;

    @GetMapping("/getServiceList")
    public String getServiceList(){
        return testEurekaService.getServiceList();

    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestPart("file")MultipartFile file){
        return testEurekaService.uploadFile(file);
    }
}
