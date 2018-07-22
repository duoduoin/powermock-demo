package com.duoduoin.mock;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.powermock.reflect.Whitebox;

public class DepartmentTest {
	@Test
	public void shouldVerifyThatNewEmployeeIsAddedToTheDepartment() {

		final Department department = new Department();
		final Employee employee = new Employee();
		department.addEmployee(employee);
		final List<Employee> employees = Whitebox.getInternalState(department, "employees");
		assertTrue(employees.contains(employee));
	}

	@Test
	public void shouldAddNewEmployeeToTheDepartment() {

		final Department department = new Department();
		final Employee employee = new Employee();
		final ArrayList<Employee> employees = new ArrayList<Employee>();
		Whitebox.setInternalState(department, "employees", employees);
		department.addEmployee(employee);
		assertTrue(employees.contains(employee));
	}

	@Test
	public void shouldVerifyThatMaxSalaryOfferedForADepartmentIsCalculatedCorrectly() throws Exception {

		final Department department = new Department();
		final Employee employee1 = new Employee();
		final Employee employee2 = new Employee();
		employee1.setSalary(60000);
		employee2.setSalary(65000);
		// Adding two employees to the test employees list.
		final ArrayList<Employee> employees = new ArrayList<Employee>();
		employees.add(employee1);
		employees.add(employee2);
		Whitebox.setInternalState(department, "employees", employees);
		Whitebox.invokeMethod(department, "updateMaxSalaryOffered");
		final long maxSalary = Whitebox.getInternalState(department, "maxSalaryOffered");
		assertEquals(65000, maxSalary);
	}
}
