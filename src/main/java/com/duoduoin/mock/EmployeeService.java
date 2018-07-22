package com.duoduoin.mock;

public class EmployeeService {

	public int getEmployeeCount() {
		return Employee.count();
	}

	public void saveEmployee(Employee employee) {
		if (employee.isNew()) {
			createEmployee(employee);
			return;
		}
		employee.update();
	}

	private void createEmployee(Employee employee) {
		employee.setEmployeeId(EmployeeIdGenerator.getNextId());
		employee.create();
		WelcomeEmail emailSender = new WelcomeEmail(employee, "Welcome to Mocking with PowerMock How-to!");
		emailSender.send();
	}

	public boolean giveIncrementToAllEmployeesOf(int percentage) {
		try {
			Employee.giveIncrementOf(percentage);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Employee findEmployeeByEmail(String email) {
		throw new UnsupportedOperationException();
	}

	public boolean employeeExists(Employee employee) {
		throw new UnsupportedOperationException();
	}

}
