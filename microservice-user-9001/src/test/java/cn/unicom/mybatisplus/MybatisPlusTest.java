package cn.unicom.mybatisplus;

import cn.unicom.microservice.main.UserApplication;
import cn.unicom.microservice.service.ISysUserService;
import cn.unicom.microservice.vo.UserVo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
/*        SysUser user = sysUserService.getOne(Wrappers.<SysUser>lambdaQuery()
               .eq(SysUser::getUsername,"admin"));
        System.out.println("user = " + user);*/
        UserVo userVo=new UserVo();

        IPage<UserVo> sysUserByPage = sysUserService.getSysUserByPage(1, 20, userVo);
        List<UserVo> records = sysUserByPage.getRecords();
        records.forEach(System.out::println);
/*        for(UserVo user:records){
            System.out.println(user.getSysDept().getDeptname()+"-----"+user.getRealname());
        }*/


    }
}
