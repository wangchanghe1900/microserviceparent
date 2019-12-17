package cn.unicom.jwt.test;

import cn.unicom.microservice.OauthApplication;
import cn.unicom.microservice.utils.JwtOperator;
import com.jayway.jsonpath.internal.filter.ValueNode;
import io.jsonwebtoken.Claims;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.lang.model.SourceVersion;
import java.util.*;

/**
 * @author 王长河
 * @create 2019-11-27 13:11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {OauthApplication.class})
public class JWTTest {
    @Autowired
    private JwtOperator jwtOperator;

    @Test
    public void jwttest(){
        Map<String,Object> map=new HashMap<>();
        map.put("id","11111");
        map.put("role","admin");
        String token = jwtOperator.generateToken(map);
        System.out.println(token);
        Claims claims= jwtOperator.getClaimsFromToken(token);
        System.out.println("claimsFromToken = " + claims);
        System.out.println(claims.get("role"));
    }





}
