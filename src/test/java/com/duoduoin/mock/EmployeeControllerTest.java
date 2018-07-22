package com.duoduoin.mock;

import static org.junit.Assert.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;

public class EmployeeControllerTest {
	@Test
	public void shouldReturnProjectedCountOfEmployeesFromTheService() {

		EmployeeService mock = PowerMockito.mock(EmployeeService.class);
		PowerMockito.when(mock.getEmployeeCount()).thenReturn(8);
		EmployeeController employeeController = new EmployeeController(mock);
		assertEquals(10, employeeController.getProjectedEmployeeCount());
	}

	@Test
	public void shouldInvokeSaveEmployeeOnTheServiceWhileSavingTheEmployee() {

		EmployeeService mock = PowerMockito.mock(EmployeeService.class);
		EmployeeController employeeController = new EmployeeController(mock);
		Employee employee = new Employee();
		employeeController.saveEmployee(employee);
		Mockito.verify(mock).saveEmployee(employee);
	}

	@Test
	public void shouldFindEmployeeByEmail() {

		final EmployeeService mock = PowerMockito.mock(EmployeeService.class);
		final Employee employee = new Employee();
		PowerMockito.when(mock.findEmployeeByEmail(Mockito.startsWith("deep"))).thenReturn(employee);
		final EmployeeController employeeController = new EmployeeController(mock);
		assertSame(employee, employeeController.findEmployeeByEmail("deep@gitshah.com"));
		assertSame(employee, employeeController.findEmployeeByEmail("deep@packtpub.com"));
		assertNull(employeeController.findEmployeeByEmail("noreply@packtpub.com"));
	}

	@Test
	public void shouldReturnNullIfNoEmployeeFoundByEmail() {

		final EmployeeService mock = PowerMockito.mock(EmployeeService.class);
		PowerMockito.when(mock.findEmployeeByEmail(Mockito.anyString())).thenReturn(null);
		final EmployeeController employeeController = new EmployeeController(mock);
		assertNull(employeeController.findEmployeeByEmail("deep@gitshah.com"));
		assertNull(employeeController.findEmployeeByEmail("deep@packtpub.com"));
		assertNull(employeeController.findEmployeeByEmail("noreply@packtpub.com"));
	}

	@Test
	public void shouldFindEmployeeByEmailUsingTheAnswerInterface() {

		final EmployeeService mock = PowerMockito.mock(EmployeeService.class);
		final Employee employee = new Employee();

		PowerMockito.when(mock.findEmployeeByEmail(Mockito.anyString())).then(new Answer<Employee>() {

			public Employee answer(InvocationOnMock invocation) throws Throwable {
				final String email = (String) invocation.getArguments()[0];
				if (email == null)
					return null;
				if (email.startsWith("deep"))
					return employee;
				if (email.endsWith("packtpub.com"))
					return employee;
				return null;
			}
		});

		final EmployeeController employeeController = new EmployeeController(mock);
		assertSame(employee, employeeController.findEmployeeByEmail("deep@gitshah.com"));
		assertSame(employee, employeeController.findEmployeeByEmail("deep@packtpub.com"));
		assertNull(employeeController.findEmployeeByEmail("hello@world.com"));
	}

	@Test
	public void shouldReturnCountOfEmployeesFromTheServiceWithDefaultAnswer() {

		EmployeeService mock = PowerMockito.mock(EmployeeService.class, new Answer() {

			public Object answer(InvocationOnMock invocation) {
				return 10;
			}
		});

		EmployeeController employeeController = new EmployeeController(mock);
		assertEquals(12, employeeController.getProjectedEmployeeCount());
	}

}
