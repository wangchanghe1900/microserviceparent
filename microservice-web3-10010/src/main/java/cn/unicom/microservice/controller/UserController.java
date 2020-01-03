package cn.unicom.microservice.controller;

import cn.unicom.microservice.entity.UserInfo;
import cn.unicom.microservice.web.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

/**
 * @author 王长何
 * @create 2019-12-23 16:30
 */
@Controller
public class UserController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/userList")
    public String userList()throws Exception{
        return "user/userList";
    }

    @GetMapping("/getUserList")
    @ResponseBody
    public WebResponse getUserList(int page, int limit, UserInfo userInfo){
        MultiValueMap<String,Object> postParameters=new LinkedMultiValueMap<>();
        postParameters.add("page",page);
        postParameters.add("limit",limit);
        postParameters.add("userInfo",userInfo);
        HttpEntity<MultiValueMap<String, Object>> requestEntity=new HttpEntity<>(postParameters);
        WebResponse userResponse = restTemplate.postForObject("http://127.0.0.1:9001/v1/getUserList", requestEntity,WebResponse.class);
        if(userResponse != null){
            if(userResponse.getCode()==200 )
                 userResponse.setCode(0);
        }
        return userResponse;
    }

}
