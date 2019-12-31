package cn.unicom.microservice.service.impl;

import cn.unicom.microservice.entity.SysMenu;
import cn.unicom.microservice.mapper.SysMenuMapper;
import cn.unicom.microservice.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
