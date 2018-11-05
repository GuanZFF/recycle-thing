package pers.zhenfeng.service.controller;

import org.springframework.web.bind.annotation.RestController;
import pers.zhenfeng.service.service.UserService;

import javax.annotation.Resource;

/**
 * @author Grow-Worm
 * @date 2018/11/05
 */
@RestController("recycle")
public class RecycleController {

    @Resource
    private UserService userService;

}
