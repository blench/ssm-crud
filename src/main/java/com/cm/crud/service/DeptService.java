package com.cm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.crud.bean.Dept;
import com.cm.crud.dao.DeptMapper;

@Service
public class DeptService {
	
	@Autowired
	DeptMapper deptDao;
	
	public List<Dept> getAll()
	{
		return deptDao.selectByExample(null);
	}
}
