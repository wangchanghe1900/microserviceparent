package cn.unicom.microservice.controller;

import cn.unicom.microservice.entity.SysMenu;
import cn.unicom.microservice.service.ISysMenuService;
import cn.unicom.microservice.web.Response;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王长何
 * @create 2020-01-06 12:26
 */
@RestController
@RequestMapping("/v1")
@Slf4j
public class PermissionController {
    @Autowired
    private ISysMenuService sysMenuService;

    @PostMapping("/getAllPermiss")
    public Response getAllPermission(){
        try {
            Wrapper<SysMenu> queryWrapper = new QueryWrapper<>();
            List<SysMenu> sysMenus = sysMenuService.list(queryWrapper);
            if(sysMenus!=null){
                List<String> sysmenuList=new ArrayList<>();
                List<String> stringList = sysMenus.stream().map(menu -> menu.getPerms()).collect(Collectors.toList());
                //stringList.forEach(System.out::println);
                return new Response(200,"",stringList);
            }else{
                return new Response(300,"没有权限数据");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return new Response(500,"系统错误");
        }
    }

    @PostMapping("/getTopSysMenuByName/{username}")
    public Response getTopSysMenuByName(@PathVariable("username") String username){
        //sysMenuService
        try{
            List<SysMenu> sysMenulist = sysMenuService.getTopSysMenuByName(username);
            if(sysMenulist!=null){
                return new Response(200,"",sysMenulist);
            }else{
                return new Response(300,"没有权限数据");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return new Response(500,"系统错误");
        }
    }
    @PostMapping("/getAllSysMenu")
    public Response getAllSysMenu(){
        try{
            QueryWrapper<SysMenu> queryWrapper=new QueryWrapper<>();
            List<SysMenu> sysMenulist = sysMenuService.list(queryWrapper);
            if(sysMenulist!=null){
                return new Response(200,"",sysMenulist);
            }else{
                return new Response(300,"没有权限数据");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return new Response(500,"系统错误");
        }

    }
}
