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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("login-name");
		String email = request.getParameter("login-email");
		String password = request.getParameter("login-pass");
		
		UserDao udao = new UserDao(DBconnection.getConnection());
		boolean result = udao.userRegister(name, email, password);
		
		
		if(result) {
			response.sendRedirect("login.jsp");
		}
		else {
			PrintWriter pw = response.getWriter();
			pw.print("<h3 style='color:crimson; text-align:center'>Registration Failed</h3>");
			RequestDispatcher rd = request.getRequestDispatcher("/registerhere.jsp");
			rd.include(request, response);
		}
	}

}
