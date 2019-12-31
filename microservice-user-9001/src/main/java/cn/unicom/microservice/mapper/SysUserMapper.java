package cn.unicom.microservice.mapper;

import cn.unicom.microservice.entity.SysUser;
import cn.unicom.microservice.vo.UserInfo;
import cn.unicom.microservice.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author wangchanghe
 * @since 2019-12-14
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    public IPage<UserVo> getSysUserByPage(Page<UserVo> page,@Param(Constants.WRAPPER) Wrapper<UserVo> wrapper);

    public IPage<UserInfo> getUserInfoByPage(Page<UserInfo> page, @Param(Constants.WRAPPER) Wrapper<UserInfo> wrapper);

}
