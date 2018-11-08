package cn.com.onlinetool.rest;

import cn.com.onlinetool.common.base.BaseRest;
import cn.com.onlinetool.dto.Test1DTO;
import cn.com.onlinetool.service.Test1Service;
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
@RequestMapping("/api/test1")
public class TestRest1 extends BaseRest<Test1Service, Test1DTO> {

    @GetMapping("/getTest")
    public String getTest(){
        List<Test1DTO> test1DTOList = myService.find();
        return JSON.toJSONString(test1DTOList);
    }
}
