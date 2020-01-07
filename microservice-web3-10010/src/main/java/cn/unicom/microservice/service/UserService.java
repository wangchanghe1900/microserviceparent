package cn.unicom.microservice.service;

import cn.unicom.microservice.entity.UserInfo;
import cn.unicom.microservice.web.Response;
import cn.unicom.microservice.web.WebResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${microservice.user.url}")
    private String userURL;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 根据用户名查询用户信息
     * @param name
     * @return
     */

    public UserInfo getUserInfoByName(String name){
        MultiValueMap<String,Object> postParameters=new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> requestEntity=new HttpEntity<>(postParameters);
        Response userResponse = restTemplate.postForObject(userURL+"getUserInfoByName/"+name, requestEntity, Response.class);
        //System.out.println("userResponse = " + userResponse);
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

    /**
     * 根据条件查询用户信息（带分页）
     * @param page
     * @param limit
     * @param userInfo
     * @return
     */
    public WebResponse getUserList(int page, int limit, UserInfo userInfo){
        MultiValueMap<String,Object> postParameters=new LinkedMultiValueMap<>();
        postParameters.add("page",page);
        postParameters.add("limit",limit);
        postParameters.add("userInfo",userInfo);
        HttpEntity<MultiValueMap<String, Object>> requestEntity=new HttpEntity<>(postParameters);
        WebResponse userResponse = restTemplate.postForObject(userURL+"getUserList", requestEntity,WebResponse.class);
        return userResponse;
    }
}
