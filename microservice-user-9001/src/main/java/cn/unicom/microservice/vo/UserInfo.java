package cn.unicom.microservice.vo;

import cn.unicom.microservice.entity.SysUser;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @author 王长何
 * @create 2019-12-31 16:58
 */
@Data
public class UserInfo extends SysUser {
    @TableField(exist = false)
    private String deptname;
}
