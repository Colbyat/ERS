package com.test;

import com.model.Reimbursement;
import org.junit.Test;
import org.junit.Assert;

public class TestReimbursement {

	Reimbursement r = new Reimbursement(30, "", 100f, "type", "imageUrl", "description", "gradingFormat", false, "resolvingManager");
	
	@Test
	public void testToString() {
		Assert.assertEquals(r.toString(), "Reimbursement [id=30, submitter=Employee "
				+ "[username=null, password=null, firstName=null, lastName=null, email=null, balance=0.0, manager=null, isManager=false], "
				+ "cost=100.0, type=type, imageURL=imageUrl, description=description, gradingFormat=gradingFormat, isPending=false, "
				+ "resolvingManager=resolvingManager]");
	}
	
	@Test
	public void testEquals() {
		Reimbursement r1 = new Reimbursement(30, "", 100f, "type", "imageUrl", "description", "gradingFormat", false, "resolvingManager");
		Reimbursement r2 = new Reimbursement(31, "", 100f, "type", "imageUrl", "description", "gradingFormat", false, "resolvingManager");
		Assert.assertTrue(r.equals(r1));
		Assert.assertFalse(r.equals(r2));
	}	
	
	//test getters
	
	@Test
	public void testGetId() {
		Assert.assertEquals(r.getId(), 30);
	}
	
	@Test
	public void testGetSubmitter() {
		Assert.assertEquals(r.getSubmitter(), "");
	}
	
	@Test
	public void testGetCost() {
		Assert.assertTrue(r.getCost()==100f);
	}
	
	@Test
	public void testGetType() {
		Assert.assertEquals(r.getType(), "type");
	}
	
	@Test
	public void testGetImageURL() {
		Assert.assertEquals(r.getImageURL(), "imageUrl");
	}
	
	@Test
	public void testGetDescription() {
		Assert.assertEquals(r.getDescription(), "description");
	}
	
	@Test
	public void testGetGradingFormat() {
		Assert.assertEquals(r.getGradingFormat(), "gradingFormat");
	}
	
	@Test
	public void testIsPending() {
		Assert.assertFalse(r.isPending());
	}
	
	@Test
	public void testGetResolvingManager() {
		Assert.assertEquals(r.getResolvingManager(), "resolvingManager");
	}
	
	//test setters
	
	@Test
	public void testSetId() {
		r.setId(31);
		Assert.assertEquals(r.getId(), 31);
	}
	
	@Test
	public void testSetSubmitter() {
		r.setSubmitter("1");
		Assert.assertEquals(r.getSubmitter(), "1");
	}
	
	@Test
	public void testSetCost() {
		r.setCost(101f);
		Assert.assertTrue(r.getCost()==101f);
	}
	
	@Test
	public void testSetType() {
		r.setType("type1");
		Assert.assertEquals(r.getType(), "type1");
	}
	
	@Test
	public void testSetImageURL() {
		r.setImageURL("imageUrl1");
		Assert.assertEquals(r.getImageURL(), "imageUrl1");
	}
	
	@Test
	public void testSetDescription() {
		r.setDescription("description1");
		Assert.assertEquals(r.getDescription(), "description1");
	}
	
	@Test
	public void testSetGradingFormat() {
		r.setGradingFormat("gradingFormat1");
		Assert.assertEquals(r.getGradingFormat(), "gradingFormat1");
	}
	
	@Test
	public void testSetIsPending() {
		r.setPending(true);
		Assert.assertTrue(r.isPending());
	}
	
	@Test
	public void testSetResolvingManager() {
		r.setResolvingManager("resolvingManager1");
		Assert.assertEquals(r.getResolvingManager(), "resolvingManager1");
	}
	
}
