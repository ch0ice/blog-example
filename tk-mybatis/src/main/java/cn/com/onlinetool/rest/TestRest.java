package cn.com.onlinetool.rest;

import cn.com.onlinetool.entity.User;
import cn.com.onlinetool.entity.UserInfo;
import cn.com.onlinetool.service.UserInfoService;
import cn.com.onlinetool.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * @author choice
 * @description:
 * @date 2018-12-13 11:13
 *
 */
@RestController
public class TestRest {
    @Autowired
    UserService userService;
    @Autowired
    UserInfoService userInfoService;

    @GetMapping("/getUser/{id}")
    public ResponseEntity getUser(@PathVariable String id){
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getUsers")
    public ResponseEntity getUser(){
        List<User> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/getUserInfo/{userId}")
    public ResponseEntity getUserInfo(@PathVariable String userId){
        UserInfo userInfo = userInfoService.findById(userId);
        return ResponseEntity.ok(userInfo);
    }


}
