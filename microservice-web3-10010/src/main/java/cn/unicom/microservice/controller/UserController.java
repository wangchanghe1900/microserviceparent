package cn.unicom.microservice.controller;

import cn.unicom.microservice.entity.SysUser;
import cn.unicom.microservice.web.Response;
import cn.unicom.microservice.web.WebResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * @author 王长何
 * @create 2019-12-23 16:30
 */
@Controller
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    @GetMapping("/userList")
    public String userList()throws Exception{
        return "user/userList";
    }

    @GetMapping("/getUsertList")
    @ResponseBody
    public WebResponse getUsertList(int page,int limit){
        System.out.println("page = " + page + ", limit = " + limit);
        WebResponse webResponse=new WebResponse();
        Response userResponse = restTemplate.getForObject("http://127.0.0.1:9001/v1/getUserList", Response.class,page,limit);
        if(userResponse!=null){
            if(userResponse.getCode()==200){
                webResponse.setCode(userResponse.getCode());
                SysUser[] users=gson.fromJson(userResponse.getData().toString(),SysUser[].class);
                webResponse.setCount(users.length);
                List<SysUser> userList= Arrays.asList(users);
            }
        }
        return webResponse;
    }

}
