package cn.unicom.microservice.service;

import cn.unicom.microservice.entity.UserInfo;
import cn.unicom.microservice.web.Response;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author 王长何
 * @create 2020-01-03 10:23
 */
@Service
public class UserService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    public UserInfo getUserInfoByName(String name){
        MultiValueMap<String,Object> postParameters=new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> requestEntity=new HttpEntity<>(postParameters);
        Response userResponse = restTemplate.postForObject("http://127.0.0.1:9001/v1/getUserInfoByName/"+name, requestEntity, Response.class);
        System.out.println("userResponse = " + userResponse);
        if(userResponse!=null){
            if(userResponse.getCode()==200){
                Object data = userResponse.getData();
                String s = JSONArray.toJSONString(data);
                UserInfo userInfo = JSON.parseObject(s,new TypeReference<UserInfo>() {});//gson.fromJson(jsonstr, UserInfo.class);
                return userInfo;
            }
        }
        return null;
    }
}
