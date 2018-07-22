package com.duoduoin.mock;

public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {

        this.employeeService = employeeService;
    }

    public int getProjectedEmployeeCount() {

        final int actualEmployeeCount = employeeService.getEmployeeCount();
        return (int) Math.ceil(actualEmployeeCount * 1.2);
    }

    public void saveEmployee(Employee employee) {

        employeeService.saveEmployee(employee);
    }    
    
    public Employee findEmployeeByEmail(String email) {    
        return employeeService.findEmployeeByEmail(email);
    }

    public boolean isEmployeeEmailAlreadyTaken(String email) {
        Employee employee = new Employee();
        return employeeService.employeeExists(employee);
    }
}
