package cn.com.onlinetool.rest;

import cn.com.onlinetool.common.base.BaseRest;
import cn.com.onlinetool.dto.UserDTO;
import cn.com.onlinetool.service.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author choice
 * @description:
 * @date 2018/11/9 下午2:45
 *
 */
@RestController
@RequestMapping("/api")
public class UserRest extends BaseRest<UserService, UserDTO> {


    @GetMapping("/test")
    public String test(){
        List<UserDTO> userList = myService.find();
        return JSON.toJSONString(userList);
    }

    @GetMapping("/example")
    public String testExample(){

        return myService.test();
    }
}
