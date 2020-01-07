package cn.unicom.microservice.service;

import cn.unicom.microservice.entity.NavsInfo;
import cn.unicom.microservice.entity.NavsMenuInfo;
import cn.unicom.microservice.entity.SysMenu;
import cn.unicom.microservice.web.Response;
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

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王长何
 * @create 2020-01-03 17:03
 */
@Service
public class SysMenuService {
    @Value("${microservice.user.url}")
    private String userURL;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 根据用户ID查询用户权限
     * @param userId
     * @return
     */
/*    public List<String> getSysMenuList(Long userId){
        return null;
    }*/

    /**
     * 查询所有权限
     * @return
     */
    public List<String> getAllSysMenuList(){
        //getAllPermiss
        MultiValueMap<String,Object> postParameters=new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> requestEntity=new HttpEntity<>(postParameters);
        Response menuResponse = restTemplate.postForObject(userURL+"getAllPermiss", requestEntity, Response.class);
        if(menuResponse!=null){
            if(menuResponse.getCode()==200){
                Object data = menuResponse.getData();
                String s = JSONArray.toJSONString(data);
                List<String> menus= JSON.parseObject(s,new TypeReference<List<String>>() {});//gson.fromJson(jsonstr, UserInfo.class);
                return menus;
            }
        }
        return null;
    }

    public List<SysMenu> getTopSysMenuList(String username){
        MultiValueMap<String,Object> postParameters=new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> requestEntity=new HttpEntity<>(postParameters);
        Response topmenuResponse = restTemplate.postForObject(userURL+"getTopSysMenuByName/"+username, requestEntity, Response.class);
        if(topmenuResponse!=null){
            if(topmenuResponse.getCode()==200){
                Object data = topmenuResponse.getData();
                String s = JSONArray.toJSONString(data);
                List<SysMenu> menus= JSON.parseObject(s,new TypeReference<List<SysMenu>>() {});//gson.fromJson(jsonstr, UserInfo.class);
                return menus;
            }
        }
        return null;
    }

    public List<NavsMenuInfo> getAllNavsMenu(){
        MultiValueMap<String,Object> postParameters=new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, Object>> requestEntity=new HttpEntity<>(postParameters);
        Response topmenuResponse = restTemplate.postForObject(userURL+"getAllSysMenu", requestEntity, Response.class);
        if(topmenuResponse!=null){
            if(topmenuResponse.getCode()==200){
                Object data = topmenuResponse.getData();
                String s = JSONArray.toJSONString(data);
                List<SysMenu> menus= JSON.parseObject(s,new TypeReference<List<SysMenu>>() {});//gson.fromJson(jsonstr, UserInfo.class);
                return sysMenu2NavsInfo(0L,menus);
            }
        }
        return null;
    }

    private List<NavsMenuInfo> sysMenu2NavsInfo(Long parentId,List<SysMenu> menus){
        List<SysMenu> submenus = menus.stream().filter(menu -> menu.getParentId() == parentId).collect(Collectors.toList());
        List<NavsMenuInfo> navsMenuInfos=new ArrayList<>();
        //List<String> stringList = topmenus.stream().map(menu -> menu.getName()).collect(Collectors.toList());
        for (int i = 0; i <submenus.size() ; i++) {
            NavsMenuInfo navsMenuInfo=new NavsMenuInfo();
            navsMenuInfo.setParentName(submenus.get(i).getName());
            Long pId=submenus.get(i).getId();
            navsMenuInfo.setNavsList(getNavsinfos(pId.intValue(),menus));
/*            List<NavsInfo> navs=new ArrayList<>();
            List<SysMenu> subsubmenus = menus.stream().filter(menu -> menu.getParentId() == pId).collect(Collectors.toList());
            for(int j=0;j<subsubmenus.size();j++){
                NavsInfo info=new NavsInfo();
                info.setTitle(subsubmenus.get(j).getName());
                info.setIcon(subsubmenus.get(j).getIcon());
                info.setHref(subsubmenus.get(j).getUrl());
                info.setSpread(false);
                navs.add(info);
                navsMenuInfo.setNavsList(navs);
            }*/
            navsMenuInfos.add(navsMenuInfo);

        }
        return  navsMenuInfos;
    }

    private List<NavsInfo> getNavsinfos(int pId,List<SysMenu> menus){
        List<SysMenu> submenus = menus.stream().filter(menu -> menu.getParentId() == pId).collect(Collectors.toList());
        List<NavsInfo> navsList=new ArrayList<>();
        for (SysMenu s:submenus) {
            NavsInfo navsInfo=new NavsInfo();
            navsInfo.setTitle(s.getName());
            navsInfo.setIcon(s.getIcon());
            navsInfo.setHref(s.getUrl());
            navsInfo.setSpread(false);
            navsInfo.setChildren(getNavsinfos(s.getId().intValue(),menus));
            navsList.add(navsInfo);
        }

        return navsList;
    }
}
