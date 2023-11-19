package com.EcommerceStore.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.EcommerceStore.connection.DBconnection;
import com.EcommerceStore.dao.OrderDao;

@WebServlet("/CancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		if(id != null) {
			try {
				OrderDao orderDao = new OrderDao(DBconnection.getConnection());
				orderDao.cancelOrder(Integer.parseInt(id));
				
			} catch (Exception e) {
				
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("/orders.jsp");
		rd.include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
