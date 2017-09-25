import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cm.crud.bean.Employee;
import com.github.pagehelper.PageInfo;

@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MVCTest {
	//����springmvc��ioc
	@Autowired
	WebApplicationContext context;
	//����mvc���󣬻�ȡ��������
	MockMvc mockMvc;
	
	@Before
	public void init()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testPage() throws Exception
	{
		//ģ�������õ�����ֵ
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1"))
				.andReturn();
		
		//�������У�����pageInfo������
		MockHttpServletRequest request = result.getRequest();
		PageInfo<?> pi = (PageInfo<?>) request.getAttribute("pageInfo");
		System.out.println("��ǰҳ�룺"+pi.getPageNum());
		System.out.println("��ҳ�룺"+pi.getPages());
		System.out.println("�ܼ�¼����"+pi.getTotal());
		int[] nums = pi.getNavigatepageNums();
		for(int i: nums)
		{
			System.out.print("\t"+i);
		}
		System.out.println();
		//�õ�Ա����Ϣ
		List<Employee> emps = (List<Employee>)pi.getList();
		for(Employee emp:emps)
		{
			System.out.println("Ա������:"+emp.getEmpName());
			System.out.println("Ա������:"+emp.getEmail());
			System.out.println("Ա���Ա�:"+emp.getGender());
			System.out.println("Ա������:"+emp.getDept().getDeptName());
		}
	}
}
