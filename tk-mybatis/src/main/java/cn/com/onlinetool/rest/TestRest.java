package cn.com.onlinetool.rest;

import cn.com.onlinetool.common.base.BaseRest;
import cn.com.onlinetool.dto.TestDTO;
import cn.com.onlinetool.service.TestService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author choice
 * @description:
 * @date 2018/11/8 下午8:37
 *
 */
@RestController
@RequestMapping("/api/test")
public class TestRest extends BaseRest<TestService, TestDTO> {

    @GetMapping("/getTest")
    public String getTest(){
        List<TestDTO> testDTOS = myService.find();
        return JSON.toJSONString(testDTOS);
    }
}
