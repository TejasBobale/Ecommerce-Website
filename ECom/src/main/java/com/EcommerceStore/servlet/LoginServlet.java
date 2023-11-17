package com.EcommerceStore.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.EcommerceStore.connection.DBconnection;
import com.EcommerceStore.dao.UserDao;
import com.EcommerceStore.model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		String email = request.getParameter("login-email");
		String password = request.getParameter("login-pass");
		
		UserDao udao = new UserDao(DBconnection.getConnection());
		User userDetails = udao.userLogin(email, password);
		
		if(userDetails != null) {
			request.getSession().setAttribute("auth", userDetails);	
			response.sendRedirect("index.jsp");
		}
		else {
			response.sendRedirect("login.jsp");
		}
		 
	}

}
