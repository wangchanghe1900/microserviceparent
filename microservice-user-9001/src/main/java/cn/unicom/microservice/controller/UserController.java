package cn.unicom.microservice.controller;

import cn.unicom.microservice.entity.SysUser;
import cn.unicom.microservice.service.ISysUserService;
import cn.unicom.microservice.web.Response;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王长何
 * @create 2019-12-14 18:43
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class UserController {
    @Autowired
    private ISysUserService sysUserService;
    @PostMapping("/getUserByName")
    public Response login(String userName,String password) {
        try {
            SysUser user = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().eq(SysUser::getUsername, userName));
            if(user!=null ){
                String salt = user.getSalt();
                String passwordhex = DigestUtils.md5DigestAsHex((password + salt).getBytes());
                if(passwordhex.equals(user.getPassword())){
                    return new Response(200,"",user);
                }else{
                    return new Response(500,"用户名或密码不正确！",null);
                }
            }else{
                return new Response(500,"用户不存在！",null);
            }
        } catch (Exception e) {
            log.error("login:"+e.getMessage());
            return new Response(500,"系统错误！",null);
        }

    }
    @GetMapping("/getUserById/{id}")
    public Response getUserById(@PathVariable("id") Long id){
        try {
            SysUser user = sysUserService.getById(id);
            if(user!=null ){
                return new Response(200,"",user);
            }else{
                return new Response(500,"用户不存在！",null);
            }
        } catch (Exception e) {
            log.error("login:"+e.getMessage());
            return new Response(500,"系统错误！",null);
        }
    }

    @GetMapping("/getUserList")
    public Response getUserList(int page,int limit){
        try {
            return new Response();
        }catch(Exception e){
            log.error("login:"+e.getMessage());
            return new Response(500,"系统错误！",null);
        }
    }
}
