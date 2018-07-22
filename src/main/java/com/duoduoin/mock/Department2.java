package com.duoduoin.mock;

public class Department2 extends BaseEntity {

    private int departmentId;
    private String name;

    public Department2(int departmentId) {
        super();
        this.departmentId = departmentId;
    }

    public void setName(String name) {
        this.name = name;
        super.performAudit(this.name);
    }    

    protected void performAudit(String auditInformation) {
        throw new UnsupportedOperationException();
    }

    public Object getDepartmentId() {
        return departmentId;
    }

    public Object getName() {
        return name;
    }
}