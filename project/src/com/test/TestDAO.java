package com.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.dao.DAO;
import com.dao.DAOImpl;
import com.model.Employee;
import com.model.Reimbursement;
import com.util.Encryptor;

public class TestDAO {

	DAO dao = new DAOImpl();
	
	@Test
	public void testLogin() {
		Assert.assertNull(dao.login("", ""));
		Assert.assertNull(dao.login("colbyat", ""));
		Assert.assertNotNull(dao.login("colbyat", Encryptor.encrypt("password")));
	}
	
	@Test
	public void testSubmitReimbursementRequest() {
		Reimbursement r = new Reimbursement(0, "user", 100f, "seminar", "none", "ML seminar", "None", true, "");
		Assert.assertTrue(dao.submitReimbursementRequest(r));
		Employee user = dao.login("user", Encryptor.encrypt("password"));
		Assert.assertTrue(user.getBalance()==840);
		r.setCost(10000);
		Assert.assertTrue(dao.submitReimbursementRequest(r));
		user = dao.login("user", Encryptor.encrypt("password"));
		Assert.assertTrue(user.getBalance()==0);
	}
	
	@Test
	public void testGetPending() {
		List<Reimbursement> r = dao.getPending("user");
		for(Reimbursement r1:r)
			Assert.assertEquals(r1.getSubmitter(), "user");
	}
	
	@Test
	public void testGetResolved() {
		List<Reimbursement> r = dao.getResolved("colbyat");
		Assert.assertTrue(r.size()==1);
	}
	
	@Test
	public void testUpdateEmployee() {
		Employee e = dao.login("user", Encryptor.encrypt("password"));
		e.setEmail("new_fake_email@email.com");
		Assert.assertTrue(dao.updateEmployee(e));
		e = dao.login("user", Encryptor.encrypt("password"));
		Assert.assertEquals(e.getEmail(), "new_fake_email@email.com");
	}
	
	@Test
	public void testUpdatePassword() {
		Assert.assertTrue(dao.updatePassword("user1", Encryptor.encrypt("newpassword")));
		Employee e = dao.login("user1", Encryptor.encrypt("newpassword"));
		Assert.assertNotNull(e);
	}
	
	@Test
	public void testApproveReimbursement() {
		Reimbursement r = dao.getPending("user").get(0);
		Assert.assertTrue(dao.approveReimbursement(r.getId(), "colbyat"));
	}
	
	@Test
	public void testDenyReimbursement() {
		Reimbursement r = dao.getPending("user").get(0);
		Assert.assertTrue(dao.denyReimbursement(r.getId()));
	}
	
	@Test
	public void testGetAllPending() {
		List<Reimbursement> rs = dao.getAllPending("colbyat");		
		for(Reimbursement r:rs)		
			Assert.assertEquals(r.getSubmitter(), "user");
	}
	
	@Test
	public void testGetAllImages() {
		List<String> images = dao.getAllImages();
		Assert.assertEquals(images.get(0), "https://thcf6.redgifs.com/DeepBeautifulCornsnake.webm");
	}
	
	@Test
	public void testGetAllResolved() {
		List<Reimbursement> rs = dao.getAllResolved();
		Assert.assertEquals(rs.get(0).getSubmitter(), "colbyat");
		Assert.assertEquals(rs.get(0).getType(), "university");
	}
	
	@Test
	public void testGetEmployeesAndManagers() {
		List<Employee> employees = dao.getEmployeesAndManagers();	
		Assert.assertEquals(employees.get(0).getUsername(), "colbyat");
		Assert.assertEquals(employees.get(0).getManager(), "");
	}
	
	@Test
	public void testRegisterEmployee() {
		//TODO
		//Assert.assertTrue(false);
	}
	
	
}
