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
	 * ɾ��Ա����Ϣ
	 * �����ɾ������
	 * 1������ɾ�������û�
	 * 2��Ҳ��������ɾ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("ids")String ids)
	{
		//����ɾ��
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
		 * ����PageHelper�����ѯ �ڼ�ҳ��ÿҳ�Ĳ�ѯ��¼
		 */
		// �ڲ�ѯ֮ǰֻ��Ҫ������ʼҳ��ÿҳ�Ĵ���
		PageHelper.startPage(pn, 5);
		// startPage����ľ��Ƿ�ҳ��ѯ
		List<Employee> employes = employeeService.getAll();
		// ʹ��pageInfo��װ��ѯ�����Ϣ��
		PageInfo page = new PageInfo(employes, 5);
		return Msg.success().add("pageInfo", page);
	}
	
	

	// @RequestMapping("/emps")
	public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {
		/**
		 * ����PageHelper�����ѯ �ڼ�ҳ��ÿҳ�Ĳ�ѯ��¼
		 */
		// �ڲ�ѯ֮ǰֻ��Ҫ������ʼҳ��ÿҳ�Ĵ���
		PageHelper.startPage(pn, 5);
		// startPage����ľ��Ƿ�ҳ��ѯ
		List<Employee> employes = employeeService.getAll();
		// ʹ��pageInfo��װ��ѯ�����Ϣ��
		PageInfo page = new PageInfo(employes, 5);
		// ϸ�ģ�����ĸ�ͻ���ɴ��
		model.addAttribute("pageInfo", page);
		return "list";
	}
	
	/**
	 * ʹ��POST��������Ա��
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
				System.out.println("����"+error.getField());
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
	 * ����id�õ�Ա����Ϣ
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
	 * ֱ�ӷ���ajax=PUT����
	 * ���͵�����
	 * Employee [empId=4, empName=null, gender=null, email=null, dId=null, dept=null]
	 * 
	 * ���⣺�������������ݣ�
	 * ��Employee�з�װ���ϣ�
	 * update tbl_emp  where emp_id = #{empId,jdbcType=INTEGER}
	 * 
	 * ԭ��
	 * Tomcat:
	 * 		1�����������������ݣ���װһ��map�У�
	 * 		2��request.getParameter("empName")�ͻ�����map��ȡֵ��
	 * 		3��SpringMVC��װPOJO�����ʱ��
	 * 				���POJO��ÿ�����Ե�ֵ��request.getParameter("email");
	 * Ajax����PUT����������Ѫ����
	 * 		��������request.getParameter("email");����Ϊ��
	 * 
	 * Tomcat һ����PUT�����޷����������е����ݽ��з�װΪmap��ֻ��POST��ʽ��������Ż��װΪmap
	 * 
	 * org.apache.catalina.connector.Request
	 * 
	 *protected String parseBodyMethods = "POST";
	 * if( !getConnector().isParseBodyMethod(getMethod()) ) {
                success = true;
                return;
            }
            
       
     *���������
     *1������Ҫ��ֱ�ӷ���PUT֮�������Ҫ��װ�������е�����
     *2��������HttpPutFormContentFilter
     *3���������ã����������е����ݽ�����װ��һ��map
     *request�����°�װ��request.getParameter()����д���ͻ��map��ȡ����
	 * �޸��û���Ϣ
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method = RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee,HttpServletRequest request)
	{
		System.out.println(request.getParameter("gender"));
		System.out.println("��Ҫ���µ�Ա�����ݣ�"+employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	/**
	 * ����û����Ƿ���ã�
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
