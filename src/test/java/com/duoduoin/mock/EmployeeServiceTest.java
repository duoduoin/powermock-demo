package com.duoduoin.mock;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Employee.class, EmployeeIdGenerator.class, EmployeeService.class })
public class EmployeeServiceTest {

	@Test
	public void shouldReturnTheCountOfEmployeesUsingTheDomainClass() {

		PowerMockito.mockStatic(Employee.class);
		PowerMockito.when(Employee.count()).thenReturn(900);

		EmployeeService employeeService = new EmployeeService();
		assertEquals(900, employeeService.getEmployeeCount());

	}

	@Test
	public void shouldReturnTrueWhenIncrementOf10PercentageIsGivenSuccessfully() {

		PowerMockito.mockStatic(Employee.class);
		PowerMockito.doNothing().when(Employee.class);
		Employee.giveIncrementOf(10);
		EmployeeService employeeService = new EmployeeService();
		assertTrue(employeeService.giveIncrementToAllEmployeesOf(10));

	}

	@Test
	public void shouldReturnFalseWhenIncrementOf10PercentageIsNotGivenSuccessfully() {

		PowerMockito.mockStatic(Employee.class);
		PowerMockito.doThrow(new IllegalStateException()).when(Employee.class);
		Employee.giveIncrementOf(10);
		EmployeeService employeeService = new EmployeeService();
		assertFalse(employeeService.giveIncrementToAllEmployeesOf(10));
	}

	@Test
	public void shouldCreateNewEmployeeIfEmployeeIsNew() throws Exception {

		Employee mock = PowerMockito.mock(Employee.class);
		PowerMockito.when(mock.isNew()).thenReturn(true);
		PowerMockito.mockStatic(EmployeeIdGenerator.class);
		WelcomeEmail welcomeEmailMock = PowerMockito.mock(WelcomeEmail.class);
		PowerMockito.whenNew(WelcomeEmail.class)
				.withArguments(mock, "Welcome to Mocking with PowerMock How-to!").thenReturn(welcomeEmailMock);
		EmployeeService employeeService = new EmployeeService();
		employeeService.saveEmployee(mock);
		Mockito.verify(mock).create();
		Mockito.verify(mock, Mockito.never()).update();
	}

	@Test
	public void shouldInvoke_giveIncrementOfMethodOnEmployeeWhileGivingIncrement() {

		PowerMockito.mockStatic(Employee.class);
		PowerMockito.doNothing().when(Employee.class);
		Employee.giveIncrementOf(9);
		EmployeeService employeeService = new EmployeeService();
		employeeService.giveIncrementToAllEmployeesOf(9);
		PowerMockito.verifyStatic();
		Employee.giveIncrementOf(9);
	}

	@Test
	public void shouldInvokeIsNewBeforeInvokingCreate() throws Exception {

		Employee mock = PowerMockito.mock(Employee.class);
		PowerMockito.when(mock.isNew()).thenReturn(true);
		PowerMockito.mockStatic(EmployeeIdGenerator.class);
		WelcomeEmail welcomeEmailMock = PowerMockito.mock(WelcomeEmail.class);
		PowerMockito.whenNew(WelcomeEmail.class)
				.withArguments(mock, "Welcome to Mocking with PowerMock How-to!").thenReturn(welcomeEmailMock);
		EmployeeService employeeService = new EmployeeService();
		employeeService.saveEmployee(mock);
		InOrder inOrder = Mockito.inOrder(mock);
		inOrder.verify(mock).isNew();
		Mockito.verify(mock).create();
		Mockito.verify(mock, Mockito.never()).update();
	}

	@Test
	public void shouldGenerateEmployeeIdIfEmployeeIsNew() throws Exception {

		Employee mock = PowerMockito.mock(Employee.class);
		PowerMockito.when(mock.isNew()).thenReturn(true);
		PowerMockito.mockStatic(EmployeeIdGenerator.class);
		WelcomeEmail welcomeEmailMock = PowerMockito.mock(WelcomeEmail.class);
		PowerMockito.whenNew(WelcomeEmail.class)
				.withArguments(mock, "Welcome to Mocking with PowerMock How-to!").thenReturn(welcomeEmailMock);
		PowerMockito.when(EmployeeIdGenerator.getNextId()).thenReturn(90);
		EmployeeService employeeService = new EmployeeService();
		employeeService.saveEmployee(mock);
		PowerMockito.verifyStatic();
		EmployeeIdGenerator.getNextId();
		Mockito.verify(mock).setEmployeeId(90);
		Mockito.verify(mock).create();
	}

	@Test
	public void shouldSendWelcomeEmailToNewEmployees() throws Exception {

		Employee employeeMock = PowerMockito.mock(Employee.class);
		PowerMockito.when(employeeMock.isNew()).thenReturn(true);
		PowerMockito.mockStatic(EmployeeIdGenerator.class);
		WelcomeEmail welcomeEmailMock = PowerMockito.mock(WelcomeEmail.class);
		PowerMockito.whenNew(WelcomeEmail.class)
				.withArguments(employeeMock, "Welcome to Mocking with PowerMock How-to!").thenReturn(welcomeEmailMock);
		EmployeeService employeeService = new EmployeeService();
		employeeService.saveEmployee(employeeMock);

		PowerMockito.verifyNew(WelcomeEmail.class).withArguments(employeeMock,
				"Welcome to Mocking with PowerMock How-to!");
		Mockito.verify(welcomeEmailMock).send();
	}
	
	
//	@Test
//    public void shouldInvokeTheCreateEmployeeMethodWhileSavingANewEmployee() {
//
//        final EmployeeService spy = PowerMockito.spy(new EmployeeService());
//        final Employee employeeMock = PowerMockito.mock(Employee.class);
//        PowerMockito.when(employeeMock.isNew()).thenReturn(true);
//        PowerMockito.doNothing().when(spy).createEmployee(employeeMock);
//        spy.saveEmployee(employeeMock);
//        Mockito.verify(spy).createEmployee(employeeMock);      
//    }
	
	@Test
    public void shouldInvokeTheCreateEmployeeMethodWhileSavingANewEmployee() throws Exception {

        final EmployeeService spy = PowerMockito.spy(new EmployeeService());
        final Employee employeeMock = PowerMockito.mock(Employee.class);
        PowerMockito.when(employeeMock.isNew()).thenReturn(true);
        PowerMockito.doNothing().when(spy, "createEmployee", employeeMock);
        spy.saveEmployee(employeeMock);
        PowerMockito.verifyPrivate(spy).invoke("createEmployee", employeeMock);
    }
}
