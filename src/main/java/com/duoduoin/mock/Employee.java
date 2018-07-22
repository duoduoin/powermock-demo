package com.duoduoin.mock;

public class Employee {
	public static int count() {
		throw new UnsupportedOperationException();
	}

	public static void giveIncrementOf(int percentage) {
		throw new UnsupportedOperationException();
	}

	private long salary;

	public void save() {
		throw new UnsupportedOperationException();
	}

	public boolean isNew() {
		throw new UnsupportedOperationException();
	}

	public void create() {
		throw new UnsupportedOperationException();
	}

	public void update() {
		throw new UnsupportedOperationException();
	}

	public void setEmployeeId(int nextId) {
		throw new UnsupportedOperationException();
	}

	public long getSalary() {
		return salary;
	}
	
	public void setSalary(int i) {
        salary = i;        
    }
}
