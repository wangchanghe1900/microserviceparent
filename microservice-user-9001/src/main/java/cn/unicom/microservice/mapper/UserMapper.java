package cn.unicom.microservice.mapper;

import cn.unicom.microservice.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 王长河
 * @create 2019-11-18 17:03
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
