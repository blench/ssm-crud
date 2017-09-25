package com.cm.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.crud.bean.Dept;
import com.cm.crud.bean.Employee;
import com.cm.crud.bean.Msg;
import com.cm.crud.service.DeptService;
import com.cm.crud.service.EmployeeService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	/**
	 * 删除员工信息
	 * 改造该删除方法
	 * 1、可以删除单个用户
	 * 2、也可以批量删除
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("ids")String ids)
	{
		//批量删除
		if(ids.contains("-"))
		{
			List<Integer> list = new ArrayList<Integer>();
			list.clear();
			String[] str_ids = ids.split("-");
			for (String id : str_ids) {
				list.add(Integer.parseInt(id));
			}
			employeeService.deleteEmpBatch(list);
			return Msg.success();
		}else
		{
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
			return Msg.success();
		}
		
	}
	
	
	@RequestMapping("/emp")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		/**
		 * 引用PageHelper插件查询 第几页，每页的查询记录
		 */
		// 在查询之前只需要设置起始页和每页的次数
		PageHelper.startPage(pn, 5);
		// startPage后面的就是分页查询
		List<Employee> employes = employeeService.getAll();
		// 使用pageInfo包装查询后的信息，
		PageInfo page = new PageInfo(employes, 5);
		return Msg.success().add("pageInfo", page);
	}
	
	

	// @RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		/**
		 * 引用PageHelper插件查询 第几页，每页的查询记录
		 */
		// 在查询之前只需要设置起始页和每页的次数
		PageHelper.startPage(pn, 5);
		// startPage后面的就是分页查询
		List<Employee> employes = employeeService.getAll();
		// 使用pageInfo包装查询后的信息，
		PageInfo page = new PageInfo(employes, 5);
		// 细心，少字母就会酿成大错
		model.addAttribute("pageInfo", page);
		return "list";
	}
	
	/**
	 * 使用POST方法保存员工
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result)
	{
		if(result.hasErrors())
		{
			Map<String,Object> map = new HashMap<String, Object>();
			List<FieldError> fieldError = result.getFieldErrors();
			for (FieldError error : fieldError) {
				System.out.println("错误"+error.getField());
				System.out.println(""+error.getDefaultMessage());
				map.put(error.getField(), error.getDefaultMessage());
			}
			return Msg.fail().add("fieldErrors", map);
		}else
		{
			employeeService.saveEmp(employee);
			return Msg.success();	
		}
		
	}
	/**
	 * 根据id得到员工信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method = RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id)
	{
		Employee emp = employeeService.getEmp(id);
		return Msg.success().add("emp", emp);
	}
	
	/**
	 * 直接发送ajax=PUT请求，
	 * 发送的数据
	 * Employee [empId=4, empName=null, gender=null, email=null, dId=null, dept=null]
	 * 
	 * 问题：请求体中有数据，
	 * 但Employee中封装不上，
	 * update tbl_emp  where emp_id = #{empId,jdbcType=INTEGER}
	 * 
	 * 原因：
	 * Tomcat:
	 * 		1、将请求体中有数据，封装一个map中，
	 * 		2、request.getParameter("empName")就会从这个map中取值，
	 * 		3、SpringMVC封装POJO对象的时候，
	 * 				会把POJO中每个属性的值，request.getParameter("email");
	 * Ajax发送PUT请求引发的血案，
	 * 		请求体中request.getParameter("email");数据为空
	 * 
	 * Tomcat 一看是PUT请求，无法对请求体中的数据进行封装为map，只有POST形式的请求体才会封装为map
	 * 
	 * org.apache.catalina.connector.Request
	 * 
	 *protected String parseBodyMethods = "POST";
	 * if( !getConnector().isParseBodyMethod(getMethod()) ) {
                success = true;
                return;
            }
            
       
     *解决方案：
     *1、我们要能直接发送PUT之类的请求还要封装请求体中的数据
     *2、配置上HttpPutFormContentFilter
     *3、它的作用，将请求体中的数据解析包装成一个map
     *request被重新包装，request.getParameter()被重写，就会从map中取数据
	 * 修改用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method = RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee,HttpServletRequest request)
	{
		System.out.println(request.getParameter("gender"));
		System.out.println("将要更新的员工数据："+employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	/**
	 * 检查用户名是否可用，
	 * @param empName
	 * @return
	 */
	@RequestMapping("/checkUser")
	@ResponseBody
	public Msg checkUser(@RequestParam("empName")String empName)
	{
		boolean b = employeeService.checkUser(empName);
		if(b)
		{
			return Msg.success();
		}else
		{
			return Msg.fail();
		}
		
	}
}
