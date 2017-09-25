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
		//���ҳ����еĲ�����Ϣ
		List<Dept> depts = deptService.getAll();
		//����Ϣ���ظ������
		return Msg.success().add("depts",depts);
	}
}
