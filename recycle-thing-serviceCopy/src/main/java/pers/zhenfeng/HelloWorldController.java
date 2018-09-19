package pers.zhenfeng;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.service.mapper.UserMapper;

import javax.annotation.Resource;

@RestController
public class HelloWorldController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/hello")
    public String helloWorld() {
        System.out.println("hello world");
        return "helloWorld";
    }

    @RequestMapping("/user")
    public UserInfo getUserInfo() {
        return userMapper.findUserInfo("2");
    }
}
