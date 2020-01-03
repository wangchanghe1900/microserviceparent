package cn.unicom.microservice.service.impl;

import cn.unicom.microservice.entity.SysUser;
import cn.unicom.microservice.mapper.SysUserMapper;
import cn.unicom.microservice.service.ISysUserService;
import cn.unicom.microservice.vo.UserInfo;
import cn.unicom.microservice.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public IPage<UserVo> getSysUserByPage(int page, int limit, UserVo userVo) {
        Page<UserVo> ipage=new Page<>(page,limit);
        QueryWrapper<UserVo> queryWrapper=new QueryWrapper<>();
        if(userVo!= null) {
            queryWrapper.eq(StringUtils.isNotEmpty(userVo.getUsername()),"username", userVo.getUsername())
                    .likeRight(StringUtils.isNotEmpty(userVo.getRealname()),"realname",userVo.getRealname())
                    .eq(StringUtils.isNotEmpty(userVo.getMobile()),"mobile", userVo.getMobile());
        }
        queryWrapper.orderByDesc("a.id");
        return sysUserMapper.getSysUserByPage(ipage,queryWrapper);
    }

    @Override
    public IPage<UserInfo> getUserInfoByPage(int page, int limit, UserInfo userInfo) {
        Page<UserInfo> ipage=new Page<>(page,limit);
        QueryWrapper<UserInfo> queryWrapper=new QueryWrapper<>();
        if(userInfo!= null) {
            queryWrapper.eq(StringUtils.isNotEmpty(userInfo.getUsername()),"username", userInfo.getUsername())
                    .likeRight(StringUtils.isNotEmpty(userInfo.getRealname()),"realname",userInfo.getRealname())
                    .eq(StringUtils.isNotEmpty(userInfo.getMobile()),"mobile", userInfo.getMobile());
        }
        queryWrapper.orderByDesc("a.id");
        return sysUserMapper.getUserInfoByPage(ipage,queryWrapper);
    }

    @Override
    public UserVo getUserVoByCondition(UserVo userVo) {
        QueryWrapper<UserVo> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotEmpty(userVo.getUsername()),"username",userVo.getUsername())
                .likeRight(StringUtils.isNotEmpty(userVo.getRealname()),"realname",userVo.getRealname())
                 .eq(StringUtils.isNotEmpty(userVo.getMobile()),"mobile",userVo.getMobile());
        return sysUserMapper.getUserInfoByCondition(queryWrapper);
    }
}
