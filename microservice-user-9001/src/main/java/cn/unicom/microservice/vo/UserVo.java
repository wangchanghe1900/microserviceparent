package cn.unicom.microservice.vo;

import cn.unicom.microservice.entity.SysDept;
import cn.unicom.microservice.entity.SysRole;
import cn.unicom.microservice.entity.SysUser;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 王长何
 * @create 2019-12-31 8:51
 */
@Data
public class UserVo extends SysUser implements Serializable {
    private static final long serialVersionUID = -5102427442733321505L;
    /**
     * 部门信息
     */
    @TableField(exist = false)
    private SysDept sysDept;
    /**
     * 角色信息
     */
    @TableField(exist = false)
    private List<SysRole> roles;



}
