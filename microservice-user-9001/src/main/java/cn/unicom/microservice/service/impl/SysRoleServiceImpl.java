package cn.unicom.microservice.service.impl;

import cn.unicom.microservice.entity.SysRole;
import cn.unicom.microservice.mapper.SysRoleMapper;
import cn.unicom.microservice.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author 王长河
 * @since 2019-12-31
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

}
