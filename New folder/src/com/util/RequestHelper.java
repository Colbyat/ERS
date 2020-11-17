package com.util;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.dao.DAO;
import com.dao.DAOImpl;
import com.model.Employee;
import com.model.Reimbursement;

public class RequestHelper {

	public static Object processGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		HttpSession session = request.getSession(false);
		final String URL = request.getRequestURI();
		final String RESOURCE = URL.split("/")[URL.split("/").length-1];
		DAO dao = new DAOImpl();
		
		System.out.println("get:"+RESOURCE);
		
		switch(RESOURCE) {
		
		//employee functions:
		
		case "mypending":
			return dao.getPending(((Employee) session.getAttribute("user")).getUsername());
		
		case "myresolved":
			return dao.getResolved(((Employee) session.getAttribute("user")).getUsername());
		
		case "myinfo":
			return dao.getEmployee(((Employee) session.getAttribute("user")).getUsername());
		
		//manager functions:
		
		case "allpending":
			return dao.getAllPending(((Employee) session.getAttribute("user")).getUsername());
			
		case "allresolved":
			return dao.getAllResolved();
			
		case "allemployees":
			return dao.getEmployeesAndManagers();
			
		default:
			return "No such endpoint or resource";
			
		}
		
	}
	
	
	public static void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		final String URL = request.getRequestURI();
		final String RESOURCE = URL.split("/")[URL.split("/").length-1];
		DAO dao = new DAOImpl();
		
		System.out.println("post:"+RESOURCE);
		
		switch(RESOURCE) {
		
		case "logout":
			if (session != null) {
				session.invalidate();
			}
            response.sendRedirect("/Project_1/index.html");
			break;
			
		case "submitrequest":
			Reimbursement re = new Reimbursement();
			Random r = new Random();

			String dir = "C://tmp/reciepts/" + r.nextInt() + ".png";

			
			request.getPart("image").write(dir);
			
			re.setSubmitter(((Employee) session.getAttribute("user")).getUsername());
			re.setCost(Float.parseFloat(request.getParameter("cost")));
			re.setType(request.getParameter("type"));
			re.setImageURL(dir);
			re.setDescription(request.getParameter("description"));
			re.setGradingFormat(request.getParameter("gradingformat"));
			
			dao.submitReimbursementRequest(re);
			response.sendRedirect("../home.html");
			break;	
			
		case "approve":
			dao.approveReimbursement(Integer.parseInt(request.getParameter("id")), ((Employee) session.getAttribute("user")).getUsername());
			response.sendRedirect("/Project_1/manager/requests/details.html");
			break;
			
		case "deny":
			dao.denyReimbursement(Integer.parseInt(request.getParameter("id")));
			response.sendRedirect("/Project_1/manager/requests/details.html");
			break;
			
		case "updateMe":
			Employee e = new Employee();
			e.setUsername(request.getParameter("username"));
			e.setFirstName(request.getParameter("firstName"));
			e.setLastName(request.getParameter("lastName"));
			e.setEmail(request.getParameter("email"));
			e.setManager(request.getParameter("manager"));
			e.setManager(Boolean.parseBoolean(request.getParameter("isManager")));
			
			dao.updateEmployee(e);
			break;
			
		}
		
	}
	
}
