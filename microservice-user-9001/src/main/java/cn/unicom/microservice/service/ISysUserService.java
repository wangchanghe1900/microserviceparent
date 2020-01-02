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
    /**
     * 跟据条件查询用户信息包含权限等
     * @param page
     * @param limit
     * @param userVo
     * @return
     */
    public IPage<UserVo> getSysUserByPage(int page, int limit, UserVo userVo);

    /**
     * 跟据条件查询用户基本信息
     * @param page
     * @param limit
     * @param userInfo
     * @return
     */
    public IPage<UserInfo> getUserInfoByPage(int page, int limit, UserInfo userInfo);

}
