package com.example.guava.cache;

import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xianpeng.xia
 * on 2022/7/10 00:35
 */
@Getter
@Setter
public class Employee {

    private String name;
    private String dept;
    private String empID;
    private final byte[] data = new byte[1024 * 1024];

    public Employee(String name, String dept, String empID) {
        this.name = name;
        this.dept = dept;
        this.empID = empID;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("name", this.getName())
            .add("department", this.getDept())
            .add("EmployeeId", this.getEmpID()).toString();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("The name " + getName() + " will be GC.");
    }
}
