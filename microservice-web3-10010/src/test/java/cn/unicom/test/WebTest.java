package cn.unicom.test;

import cn.unicom.microservice.WebApp;
import cn.unicom.microservice.service.UserService;
import cn.unicom.microservice.web.Response;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author 王长何
 * @create 2019-12-15 14:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes ={WebApp.class} )
public class WebTest {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserService userService;

    @Test
    public void restTest(){
        //String url="http://localhost:9001/v1/getUserById/1";
        //ResponseEntity<SysUser> admin = restTemplate.getForEntity(url, SysUser.class, "admin", "12#$asDF");
/*        Map<String, String> params = new HashMap<String, String>();
        params.put("userName","admin");
        params.put("password","12#$asDF");*/
        //Response res = restTemplate.getForObject(url, Response.class);
        //System.out.println("user = " + res.getData());
        //userService.getUserInfoByName("admin");
        String hashAlgorithmName = "MD5";
        String credentials = "12#$asDF";
        int hashIterations = 1;
        ByteSource credentialsSalt = ByteSource.Util.bytes("123asdf");
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(obj.toString());
    }
}
