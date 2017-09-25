import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.cm.crud.bean.Dept;
import com.cm.crud.bean.Employee;
import com.cm.crud.dao.DeptMapper;
import com.cm.crud.dao.EmployeeMapper;

/**
 * 测试dao层的工作
 * 推荐Spring的项目使用SpringTest单元测试，可以自动注入我们所需要的组件
 * 1、导入SpringTest模块
 * 2、@ContextConfiguration指定Spring配置文件的位置
 * 3、直接autowired要使用的组件即可
 * @author root
 *
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DeptMapper deptMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlSession;
	@Test
	public void testMapper()
	{
		/**
		 * 	public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.dId = dId;
	}
		 */
		//1、插入部门
//		deptMapper.insertSelective(new Dept(null,"开发部"));
//		deptMapper.insertSelective(new Dept(null,"测试部"));
		//2、插入员工
//		employeeMapper.insertSelective(new Employee(null,"zhangsan","M","zhangsan@qq.com",1));
		
		//3、批量添加员工
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++)
		{
			String uuid = UUID.randomUUID().toString().substring(0, 5)+i;
			mapper.insertSelective(new Employee(null,uuid,"M",uuid+"@qq.com",1));
		}
	}
}
