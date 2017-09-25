package com.cm.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.crud.bean.Employee;
import com.cm.crud.bean.EmployeeExample;
import com.cm.crud.bean.EmployeeExample.Criteria;
import com.cm.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	/**
	 * ��ѯ����Ա��
	 * @return
	 */
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return employeeMapper.selectByExampleWithDept(null);
	}
	
	/**
	 * ����Ա����Ϣ
	 * @param employee
	 */
	public void saveEmp(Employee employee)
	{
		employeeMapper.insertSelective(employee);
	}
	
	/**
	 * ����û����Ƿ����
	 * true ��ʾ�û������ã�����֮
	 * @param empName
	 * @return
	 */
	public boolean checkUser(String empName)
	{
		EmployeeExample example = new EmployeeExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}

	public Employee getEmp(Integer id) {
		// TODO Auto-generated method stub
		return employeeMapper.selectByPrimaryKey(id);
	}

	public void updateEmp(Employee emp) {
		// TODO Auto-generated method stub
		employeeMapper.updateByPrimaryKeySelective(emp);
	}

	public void deleteEmp(Integer id) {
		// TODO Auto-generated method stub
		employeeMapper.deleteByPrimaryKey(id);
	}

	public void deleteEmpBatch(List<Integer> list) {
		// TODO Auto-generated method stub
		EmployeeExample example = new EmployeeExample();
		Criteria createria = example.createCriteria();
		//delete from xx where emp_id in (1,2,3)
		createria.andEmpIdIn(list);
		employeeMapper.deleteByExample(example);
	}
}
