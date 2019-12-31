package cn.unicom.microservice.service.impl;

import cn.unicom.microservice.entity.SysRoleMenu;
import cn.unicom.microservice.mapper.SysRoleMenuMapper;
import cn.unicom.microservice.service.ISysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author 王长河
 * @since 2019-12-31
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService {

}
