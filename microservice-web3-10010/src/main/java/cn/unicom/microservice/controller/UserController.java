package cn.unicom.microservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 王长何
 * @create 2019-12-23 16:30
 */
@Controller
public class UserController {
    @GetMapping("/userList")
    public String userList()throws Exception{
        return "user/userList";
    }
}
