package com.cm.crud.bean;

public class Dept {
    public Dept() {
		super();
	}
	public Dept(Integer deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}

	private Integer deptId;

    private String deptName;

    public Integer getDeptId() {
        return deptId;
    }
	public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

  
	public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
}