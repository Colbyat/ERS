package com.test;

import com.model.Employee;
import org.junit.Test;
import org.junit.Assert;

public class TestEmployee {

	Employee employee = new Employee("username", "password", "first", "last", "email@email.com", 1000f, "manager", false);
	
	@Test
	public void testToString() {
		Assert.assertEquals(employee.toString(), "Employee [username=username, password=password, firstName=first, "
				+ "lastName=last, email=email@email.com, balance=1000.0, manager=manager, isManager=false]");
	}
	
	@Test
	public void testEquals() {
		Employee employee2 = new Employee("username", "password", "first", "last", "email@email.com", 1000f, "manager", false);
		Employee employee3 = new Employee("username2", "password", "first", "last", "email@email.com", 1000f, "manager", false);
		Assert.assertTrue(employee.equals(employee2));
		Assert.assertFalse(employee.equals(employee3));
	}
	
	//testing getters
	
	@Test
	public void testGetUsername() {
		Assert.assertEquals(employee.getUsername(), "username");
	}
	
	@Test
	public void testGetPassword() {
		Assert.assertEquals(employee.getPassword(), "password");
	}
	
	@Test
	public void testGetFirstName() {
		Assert.assertEquals(employee.getFirstName(), "first");
	}
	
	@Test
	public void testGetLastName() {
		Assert.assertEquals(employee.getLastName(), "last");
	}
	
	@Test
	public void testGetEmail() {
		Assert.assertEquals(employee.getEmail(), "email@email.com");
	}
	
	@Test
	public void testGetBalance() {
		Assert.assertTrue(employee.getBalance()==1000f);
	}
	
	@Test
	public void testGetManager() {
		Assert.assertEquals(employee.getManager(), "manager");
	}
	
	@Test
	public void testIsManager() {
		Assert.assertEquals(employee.isManager(), false);
	}
	
	//testing setters
	
	@Test
	public void testSetUsername() {
		employee.setUsername("employee2");
		Assert.assertEquals(employee.getUsername(), "employee2");
	}
	
	@Test
	public void testSetPassword() {
		employee.setPassword("password2");
		Assert.assertEquals(employee.getPassword(), "password2");
	}
	
	@Test
	public void testSetFirstName() {
		employee.setFirstName("first2");
		Assert.assertEquals(employee.getFirstName(), "first2");
	}
	
	@Test
	public void testSetLastName() {
		employee.setLastName("last2");
		Assert.assertEquals(employee.getLastName(), "last2");
	}
	
	@Test
	public void testSetEmail() {
		employee.setEmail("email2@email.com");
		Assert.assertEquals(employee.getEmail(), "email2@email.com");
	}
	
	@Test
	public void testSetBalance() {
		employee.setBalance(800f);
		Assert.assertTrue(employee.getBalance()==800f);
	}
	
	@Test
	public void testSetManager() {
		employee.setManager("manager2");
		Assert.assertEquals(employee.getManager(), "manager2");
	}
	
	@Test
	public void testSetIsManager() {
		employee.setManager(true);
		Assert.assertTrue(employee.isManager());
	}
	
	
}
