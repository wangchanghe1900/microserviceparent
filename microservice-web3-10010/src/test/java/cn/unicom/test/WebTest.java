package cn.unicom.test;

import cn.unicom.microservice.WebApp;
import cn.unicom.microservice.entity.NavsMenuInfo;
import cn.unicom.microservice.entity.SysMenu;
import cn.unicom.microservice.service.SysMenuService;
import cn.unicom.microservice.service.UserService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @Autowired
    private SysMenuService sysMenuService;

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
        ByteSource credentialsSalt = ByteSource.Util.bytes("678a4g3");
        Object obj = new SimpleHash(hashAlgorithmName, credentials, credentialsSalt, hashIterations);
        System.out.println(obj.toString());
    }
    @Test
    public void sysmenutest(){
        List<SysMenu> sysMenuList = sysMenuService.getTopSysMenuList("admin");
        sysMenuList.forEach(System.out::println);
    }
    @Test
    public void sysmenu2test(){
        List<NavsMenuInfo> allNavsMenu = sysMenuService.getAllNavsMenu();
        allNavsMenu.forEach(System.out::println);
    }

}
