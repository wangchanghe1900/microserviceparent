package cn.unicom.microservice.controller;

import cn.unicom.microservice.bean.User;
import cn.unicom.microservice.service.UserServicce;
import cn.unicom.web.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 王长河
 * @create 2019-11-19 9:10
 */
@RestController
@RequestMapping("/soa/v1/user")
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserServicce userServicce;

    @GetMapping("/getUser/{id}")
    public Response getUser(@PathVariable("id") Integer id){
        try {
            User user = userServicce.getUserById(id);
            if(user!=null){
                logger.info(user+"查询成功！");
                return new Response(200,"OK",user);
            }else{
                return new Response(200,"记录不存在！");
            }

        }catch (Exception e){
            logger.error(e.getMessage());
            return new Response(500,"系统错误！");
        }



    }
}
