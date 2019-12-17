package cn.unicom.mybatisplus;

import cn.unicom.microservice.entity.SysUser;
import cn.unicom.microservice.main.UserApplication;
import cn.unicom.microservice.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 王长河
 * @create 2019-11-18 17:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes ={UserApplication.class} )
public class MybatisPlusTest {
    @Autowired
    private ISysUserService sysUserService;

/*    @Resource
    private DataSource dataSource;*/

/*    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }*/

/*    @Test
    public void datasourcetest() throws Exception{
        System.out.println("数据源>>>>>>" + dataSource.getClass());
        Connection connection = dataSource.getConnection();
        System.out.println("连接>>>>>>>>>" + connection);
        System.out.println("连接地址>>>>>" + connection.getMetaData().getURL());
        DruidDataSource druidDataSource = (DruidDataSource) dataSource;
        System.out.println("druidDataSource 数据源最大连接数：" + druidDataSource.getMaxActive());
        System.out.println("druidDataSource 数据源初始化连接数：" + druidDataSource.getInitialSize());
        connection.close();
    }*/
    @Test
    public void usertest(){
        //*ysUser user = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery()
               // .eq(SysUser::getUsername,"admin"));*//*
        SysUser byId = sysUserService.getById(1L);
        System.out.println("byId = " + byId);
    }
}
