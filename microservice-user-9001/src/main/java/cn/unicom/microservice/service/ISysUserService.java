package cn.unicom.microservice.service;

import cn.unicom.microservice.entity.SysUser;
import cn.unicom.microservice.vo.UserInfo;
import cn.unicom.microservice.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author wangchanghe
 * @since 2019-12-14
 */
public interface ISysUserService extends IService<SysUser> {
    public IPage<UserVo> getSysUserByPage(int page, int limit, UserVo userVo);

    public IPage<UserInfo> getUserInfoByPage(int page, int limit, UserInfo userInfo);

}
