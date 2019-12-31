package cn.unicom.microservice.service.impl;

import cn.unicom.microservice.entity.SysUserRole;
import cn.unicom.microservice.mapper.SysUserRoleMapper;
import cn.unicom.microservice.service.ISysUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author 王长河
 * @since 2019-12-31
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

}
