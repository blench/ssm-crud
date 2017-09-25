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
 * ����dao��Ĺ���
 * �Ƽ�Spring����Ŀʹ��SpringTest��Ԫ���ԣ������Զ�ע����������Ҫ�����
 * 1������SpringTestģ��
 * 2��@ContextConfigurationָ��Spring�����ļ���λ��
 * 3��ֱ��autowiredҪʹ�õ��������
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
		//1�����벿��
//		deptMapper.insertSelective(new Dept(null,"������"));
//		deptMapper.insertSelective(new Dept(null,"���Բ�"));
		//2������Ա��
//		employeeMapper.insertSelective(new Employee(null,"zhangsan","M","zhangsan@qq.com",1));
		
		//3���������Ա��
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1000;i++)
		{
			String uuid = UUID.randomUUID().toString().substring(0, 5)+i;
			mapper.insertSelective(new Employee(null,uuid,"M",uuid+"@qq.com",1));
		}
	}
}
