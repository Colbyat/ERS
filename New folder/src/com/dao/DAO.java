package com.dao;

import java.util.List;

import com.model.Employee;
import com.model.Reimbursement;

public interface DAO {

	public Employee login(String username, String password);
	public boolean submitReimbursementRequest(Reimbursement re);
	public List<Reimbursement> getPending(String username);
	public List<Reimbursement> getResolved(String username);
	public boolean updateEmployee(Employee e);
	public boolean updatePassword(String username, String newPassword);
	public Employee getEmployee(String username);
	
	public boolean approveReimbursement(int id, String manager);
	public boolean denyReimbursement(int id);
	public List<Reimbursement> getAllPending(String manager);
	public List<String> getAllImages();
	public List<Reimbursement> getAllResolved();
	public List<Employee> getEmployeesAndManagers();
	public boolean registerEmployee(Employee e);
	
}
