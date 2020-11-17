package com.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DAO;
import com.dao.DAOImpl;
import com.model.Employee;
import com.util.Encryptor;

/**
 * Servlet implementation class Login
 */
@WebServlet({ "/Login", "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {			
		DAO dao = new DAOImpl();
		Employee e = dao.login(request.getParameter("username"), Encryptor.encrypt(request.getParameter("password")));
		
		if(e==null) {
            RequestDispatcher rs = request.getRequestDispatcher("index.html");
            request.setAttribute("message", "invalid");
            rs.include(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", e);
			
			String red = e.isManager() ? "manager/home.html":"employee/home.html";
			response.sendRedirect(red);
			
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
