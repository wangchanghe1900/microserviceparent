package cn.unicom.microservice.controller;

import cn.unicom.microservice.entity.NavsMenuInfo;
import cn.unicom.microservice.entity.SysMenu;
import cn.unicom.microservice.entity.UserInfo;
import cn.unicom.microservice.service.SysMenuService;
import cn.unicom.microservice.service.UserService;
import cn.unicom.microservice.web.WebResponse;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 王长何
 * @create 2019-12-23 16:30
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SysMenuService sysMenuService;

    @GetMapping("/userList")
    public String userList()throws Exception{
        return "user/userList";
    }

    @GetMapping("/getUserList")
    @ResponseBody
    @RequiresPermissions("userlist:list")
    public WebResponse getUserList(int page, int limit, UserInfo userInfo){
        WebResponse userResponse=userService.getUserList(page, limit, userInfo);
        if(userResponse != null){
            if(userResponse.getCode()==200 )
                 userResponse.setCode(0);
        }
        return userResponse;
    }

    @PostMapping("/getURLByName/{username}")
    @ResponseBody
    public List<SysMenu> getURLByName(@PathVariable("username") String username){
        List<SysMenu> sysMenuList = sysMenuService.getTopSysMenuList(username);
        return sysMenuList;
    }

    @GetMapping("getAllNavsMenu")
    @ResponseBody
    public List<NavsMenuInfo> getAllNavsMenu(){
        List<NavsMenuInfo> allNavsMenu = sysMenuService.getAllNavsMenu();
        return allNavsMenu;
    }

}
