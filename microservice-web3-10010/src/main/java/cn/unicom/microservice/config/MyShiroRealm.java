package cn.unicom.microservice.config;


import cn.unicom.microservice.entity.SysMenu;
import cn.unicom.microservice.entity.SysRole;
import cn.unicom.microservice.entity.UserInfo;
import cn.unicom.microservice.service.SysMenuService;
import cn.unicom.microservice.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private SysMenuService sysMenuService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> permsList;
        if(userInfo==null){
            return null;
        }
        //系统管理员，拥有最高权限
        if("admin".equals(userInfo.getUsername())){
            List<String> menuList = sysMenuService.getAllSysMenuList();
            permsList = new ArrayList<>(menuList.size());
            for(String menu : menuList){
                permsList.add(menu);
            }
            Set<String> permsSet = new HashSet<>();
            for(String perms : permsList){
                if(StringUtils.isEmpty(perms)){
                    continue;
                }
                permsSet.add(perms);
            }
            authorizationInfo.setStringPermissions(permsSet);
        }else {
            if (userInfo.getRoles() != null) {
                for (SysRole role : userInfo.getRoles()) {
                    authorizationInfo.addRole(role.getName());
                    for (SysMenu p : role.getPermissions()) {
                        authorizationInfo.addStringPermission(p.getPerms());
                    }
                }
                //permsList = sysMenuService.getSysMenuList(userInfo.getId());
            }
        }
        //用户权限列表

/*        for(SysRole role:userInfo.getRoles()){
            authorizationInfo.addRole(role.getName());
            for(SysMenu p:role.getPermissions()){
                authorizationInfo.addStringPermission(p.getPerms());
            }
        }*/

        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        //System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
        //获取用户的输入的账号.
        UsernamePasswordToken usertoken = (UsernamePasswordToken)token;
        String username=usertoken.getUsername();
        //UserInfo userInfo=new UserInfo();
        //userInfo.setUsername(usertoken.getUsername());
        //String username = (String)token.getPrincipal();
        //System.out.println(token.getCredentials().toString());
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        UserInfo userInfo =userService.getUserInfoByName(username);//userInfoService.findByUsername(username);
        //System.out.println("----->>userInfo="+userInfo);
        if(userInfo == null){
            throw new UnknownAccountException("账号或密码不正确");
        }
        if(userInfo.getStatus() == 0){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                userInfo, //用户名
                userInfo.getPassword(), //密码
                ByteSource.Util.bytes(userInfo.getSalt()),
                getName()  //realm name
        );
        return authenticationInfo;
    }

}