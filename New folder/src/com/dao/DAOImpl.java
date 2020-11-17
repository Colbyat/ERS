package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Employee;
import com.model.Reimbursement;
import com.util.ConnectionFactory;
import com.util.Encryptor;

public class DAOImpl implements DAO {
	
	Connection connection = null;
	PreparedStatement stmt = null;

	@Override
	public Employee login(String username, String password) {
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM employees WHERE username=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,username);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				if(!password.equals(rs.getString("password")))
					return null;
				
				Employee employee = new Employee();
				
				employee.setUsername(rs.getString("username"));
				employee.setPassword("");
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setEmail(rs.getString("email"));
				employee.setBalance(rs.getFloat("balance"));
				employee.setManager(rs.getString("manager"));
				employee.setManager(rs.getBoolean("ismanager"));
				
				return employee;
			} 		
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return null;
		
	}

	@Override
	public boolean submitReimbursementRequest(Reimbursement re) {
		try {
			connection = ConnectionFactory.getConnection();
			
			float cost = 0;
			
			if(re.getType().toLowerCase().equals("university"))
				cost=(float) (0.8*re.getCost());
			else if(re.getType().toLowerCase().equals("seminar"))
				cost=(float) (0.6*re.getCost());
			else if(re.getType().toLowerCase().equals("certificatePrep"))
				cost=(float) (0.75*re.getCost());
			else if(re.getType().toLowerCase().equals("certificate"))
				cost=(float) (1*re.getCost());
			else if(re.getType().toLowerCase().equals("technicalTraining"))
				cost=(float) (0.9*re.getCost());
			else
				cost=(float) (0.3*re.getCost());
			
			String sql = "SELECT balance FROM employees WHERE username=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, re.getSubmitter());
			ResultSet rs = stmt.executeQuery();
			
			rs.next();
			if(cost > rs.getFloat("balance"))
				cost = rs.getFloat("balance");
			
			sql = "INSERT INTO reimbursements VALUES("
					+ "nextval('seq'),"
					+ "(SELECT username FROM employees WHERE username=?),"
					+ "?,"
					+ "?,"
					+ "?,"
					+ "?,"
					+ "?,"
					+ "true,"
					+ "null"
					+ ")";			
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, re.getSubmitter());				
			stmt.setFloat(2, cost);
			stmt.setString(3, re.getType());
			stmt.setString(4, re.getImageURL());
			stmt.setString(5, re.getDescription());
			stmt.setString(6, re.getGradingFormat());			
			
			if (stmt.executeUpdate() == 0)
				return false;		
			
			sql = "UPDATE employees SET balance=balance-? WHERE username=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setFloat(1, cost);
			stmt.setString(2, re.getSubmitter());
			
			if (stmt.executeUpdate() != 0)
				return true;			
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return false;
	}

	@Override
	public List<Reimbursement> getPending(String username) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE ispending=true AND employee=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,username);
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				Reimbursement r = new Reimbursement();				
							
				r.setId(rs.getInt("id"));
				r.setSubmitter(rs.getString("employee"));
				r.setCost(rs.getFloat("cost"));
				r.setType(rs.getString("type"));
				r.setImageURL(rs.getString("imageurl"));
				r.setDescription(rs.getString("description"));
				r.setGradingFormat(rs.getString("gradingformat"));
				r.setPending(rs.getBoolean("ispending"));
				r.setResolvingManager(rs.getString("resolvingmanager"));
				
				reimbursements.add(r);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return reimbursements;
	}

	@Override
	public List<Reimbursement> getResolved(String username) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE ispending=false AND employee=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,username);
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				Reimbursement r = new Reimbursement();				
							
				r.setId(rs.getInt("id"));
				r.setSubmitter(rs.getString("employee"));
				r.setCost(rs.getFloat("cost"));
				r.setType(rs.getString("type"));
				r.setImageURL(rs.getString("imageurl"));
				r.setDescription(rs.getString("description"));
				r.setGradingFormat(rs.getString("gradingformat"));
				r.setPending(rs.getBoolean("ispending"));
				r.setResolvingManager(rs.getString("resolvingmanager"));
				
				reimbursements.add(r);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return reimbursements;
	}

	@Override
	public boolean updateEmployee(Employee e) {
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE employees SET "
					+ "firstname=?,"
					+ "lastname=?,"
					+ "email=?,"
					+ "manager=?,"
					+ "ismanager=?"
					+ "WHERE username=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, e.getFirstName());
			stmt.setString(2, e.getLastName());
			stmt.setString(3, e.getEmail());
			stmt.setString(4, e.getManager());
			stmt.setBoolean(5, e.isManager());
			stmt.setString(6, e.getUsername());
			
			if (stmt.executeUpdate() != 0)
				return true;			
			
		} catch(SQLException e1) {
			e1.printStackTrace();
		} finally {
			closeResources();
		}
		
		return false;
		
	}

	@Override
	public boolean updatePassword(String username, String newPassword) {
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE employees SET password=? WHERE username=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, newPassword);
			stmt.setString(2, username);
			
			if (stmt.executeUpdate() != 0)
				return true;			
			
		} catch(SQLException e1) {
			e1.printStackTrace();
		} finally {
			closeResources();
		}
		
		return false;
	}

	@Override
	public boolean approveReimbursement(int id, String manager) {
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "UPDATE reimbursements SET ispending=false, resolvingmanager=? WHERE id=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, manager);
			stmt.setInt(2, id);
			
			if (stmt.executeUpdate() != 0)
				return true;			
			
		} catch(SQLException e1) {
			e1.printStackTrace();
		} finally {
			closeResources();
		}
		
		return false;
	}

	@Override
	public boolean denyReimbursement(int id) {
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "DELETE FROM reimbursements WHERE id=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, id);
			
			if (stmt.executeUpdate() != 0)
				return true;			
			
		} catch(SQLException e1) {
			e1.printStackTrace();
		} finally {
			closeResources();
		}
		
		return false;
	}

	@Override
	public List<Reimbursement> getAllPending(String manager) {
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM reimbursements INNER JOIN employees ON "
					+ "reimbursements.employee=employees.username WHERE ispending=true AND manager=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, manager);
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				Reimbursement r = new Reimbursement();				
							
				r.setId(rs.getInt("id"));
				r.setSubmitter(rs.getString("employee"));
				r.setCost(rs.getFloat("cost"));
				r.setType(rs.getString("type"));
				r.setImageURL(rs.getString("imageurl"));
				r.setDescription(rs.getString("description"));
				r.setGradingFormat(rs.getString("gradingformat"));
				r.setPending(rs.getBoolean("ispending"));
				r.setResolvingManager(rs.getString("resolvingmanager"));
				
				reimbursements.add(r);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return reimbursements;
	}

	@Override
	public List<String> getAllImages() {
		List<String> images = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT imageurl FROM reimbursements";
			stmt = connection.prepareStatement(sql);		
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {			
				images.add(rs.getString("imageurl"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return images;
	}

	@Override
	public List<Reimbursement> getAllResolved() {
		List<Reimbursement> reimbursements = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM reimbursements WHERE ispending=false";
			stmt = connection.prepareStatement(sql);		
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				Reimbursement r = new Reimbursement();				
							
				r.setId(rs.getInt("id"));
				r.setSubmitter(rs.getString("employee"));
				r.setCost(rs.getFloat("cost"));
				r.setType(rs.getString("type"));
				r.setImageURL(rs.getString("imageurl"));
				r.setDescription(rs.getString("description"));
				r.setGradingFormat(rs.getString("gradingformat"));
				r.setPending(rs.getBoolean("ispending"));
				r.setResolvingManager(rs.getString("resolvingmanager"));
				
				reimbursements.add(r);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return reimbursements;
	}

	@Override
	public List<Employee> getEmployeesAndManagers() {
		List<Employee> employees = new ArrayList<>();
		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM employees";
			stmt = connection.prepareStatement(sql);		
			ResultSet rs = stmt.executeQuery();
		
			while(rs.next()) {
				Employee employee = new Employee();				
				
				employee.setUsername(rs.getString("username"));
				employee.setPassword("");
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setEmail(rs.getString("email"));
				employee.setBalance(rs.getFloat("balance"));
				employee.setManager(rs.getString("manager"));
				employee.setManager(rs.getBoolean("ismanager"));
				
				employees.add(employee);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return employees;
	}

	@Override
	public boolean registerEmployee(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	//code taken from pubhub project
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}

	@Override
	public Employee getEmployee(String username) {		
		try {
			connection = ConnectionFactory.getConnection();
			String sql = "SELECT * FROM employees WHERE username=?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1,username);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {				
				Employee employee = new Employee();
				
				employee.setUsername(rs.getString("username"));
				employee.setPassword("");
				employee.setFirstName(rs.getString("firstname"));
				employee.setLastName(rs.getString("lastname"));
				employee.setEmail(rs.getString("email"));
				employee.setBalance(rs.getFloat("balance"));
				employee.setManager(rs.getString("manager"));
				employee.setManager(rs.getBoolean("ismanager"));
				
				return employee;
			} 		
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return null;
		
	}

}
