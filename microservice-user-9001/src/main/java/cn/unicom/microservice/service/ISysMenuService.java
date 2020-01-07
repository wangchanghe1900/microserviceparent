package cn.unicom.microservice.service;

import cn.unicom.microservice.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author 王长河
 * @since 2019-12-31
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 根据用户名查询首页菜单
     * @param username
     * @return
     */
    public List<SysMenu> getTopSysMenuByName(String username);

}
