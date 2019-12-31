package cn.unicom.microservice.service.impl;

import cn.unicom.microservice.entity.SysDept;
import cn.unicom.microservice.mapper.SysDeptMapper;
import cn.unicom.microservice.service.ISysDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author 王长河
 * @since 2019-12-31
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {

}
