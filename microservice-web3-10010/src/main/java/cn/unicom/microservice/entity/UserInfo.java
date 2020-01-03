package cn.unicom.microservice.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 王长何
 * @create 2019-12-31 16:58
 */
@Data
public class UserInfo extends SysUser {
    private String deptname;
    private SysDept sysDept;
    private List<SysRole> roles;



}
