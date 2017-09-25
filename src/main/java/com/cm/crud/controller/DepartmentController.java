package com.cm.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cm.crud.bean.Dept;
import com.cm.crud.bean.Msg;
import com.cm.crud.service.DeptService;

@Controller
public class DepartmentController {
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping("/getDepts")
	@ResponseBody
	public Msg getDepts()
	{
		//查找出所有的部门信息
		List<Dept> depts = deptService.getAll();
		//将信息返回给浏览器
		return Msg.success().add("depts",depts);
	}
}
