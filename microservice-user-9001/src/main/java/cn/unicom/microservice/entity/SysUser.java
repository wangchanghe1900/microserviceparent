package cn.unicom.microservice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author wangchanghe
 * @since 2019-12-14
 */
@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 登录用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realname;
    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 部门ID
     */
    private Long deptId;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastlogintime;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;



}
