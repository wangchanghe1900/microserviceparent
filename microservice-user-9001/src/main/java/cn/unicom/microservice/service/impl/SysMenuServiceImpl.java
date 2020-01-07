package cn.unicom.microservice.service.impl;

import cn.unicom.microservice.entity.SysMenu;
import cn.unicom.microservice.mapper.SysMenuMapper;
import cn.unicom.microservice.service.ISysMenuService;
import cn.unicom.microservice.vo.UserInfo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author 王长河
 * @since 2019-12-31
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {
    @Autowired
    private SysMenuMapper sysMenuMapper;
    @Override
    public List<SysMenu> getTopSysMenuByName(String username) {
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        if("admin".equalsIgnoreCase(username)){
            queryWrapper
                    .eq("f.parent_id", 0)
                    .orderByAsc("f.order_num");
        }else {
            queryWrapper.eq("a.username", username)
                    .eq("f.parent_id", 0)
                    .orderByAsc("f.order_num");
        }
        return sysMenuMapper.getTopSysmenuByName(queryWrapper);
    }
}
