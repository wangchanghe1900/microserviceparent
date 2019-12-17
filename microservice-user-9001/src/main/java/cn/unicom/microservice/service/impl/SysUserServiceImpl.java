package cn.unicom.microservice.service.impl;

import cn.unicom.microservice.entity.SysUser;
import cn.unicom.microservice.mapper.SysUserMapper;
import cn.unicom.microservice.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author wangchanghe
 * @since 2019-12-14
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
