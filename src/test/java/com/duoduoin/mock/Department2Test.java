package com.duoduoin.mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.core.classloader.annotations.SuppressStaticInitializationFor;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Department2.class)
@SuppressStaticInitializationFor("com.duoduoin.mock.BaseEntity")
public class Department2Test {

	@Test
	public void shouldSuppressTheBaseConstructorOfDepartment() {
		PowerMockito.suppress(PowerMockito.constructor(BaseEntity.class));
		assertEquals(10, new Department2(10).getDepartmentId());
	}

	@Test
	public void shouldSuppressThePerformAuditMethodOfBaseEntity() {
		PowerMockito.suppress(PowerMockito.constructor(BaseEntity.class));
		PowerMockito.suppress(PowerMockito.method(BaseEntity.class, "performAudit", String.class));
		final Department2 department = new Department2(18);
		department.setName("Mocking with PowerMock");
		assertEquals("Mocking with PowerMock", department.getName());
	}

	@Test
	public void shouldSuppressTheInitializerForBaseEntity() {

		PowerMockito.suppress(PowerMockito.constructor(BaseEntity.class));
		assertNotNull(new Department2(18));
	}
}
