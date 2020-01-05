package cn.unicom.microservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 王长何
 * @create 2019-12-31 16:58
 */
@Data
@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
public class UserInfo  implements Serializable {
    private static final long serialVersionUID = 7912855138486643789L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;


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
     * 部门名称
     */
    private Long deptName;
    /**
     * 最后登录时间
     */
    private LocalDateTime lastlogintime;

    /**
     * 创建时间
     */
    private LocalDateTime createtime;
    /**
     * 部门信息
     */
    private SysDept sysDept;

    /**
     * 角色信息
     */
    private List<SysRole> roles;



}
