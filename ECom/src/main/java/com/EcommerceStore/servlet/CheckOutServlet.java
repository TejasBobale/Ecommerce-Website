package com.EcommerceStore.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.EcommerceStore.connection.DBconnection;
import com.EcommerceStore.dao.OrderDao;
import com.EcommerceStore.model.Cart;
import com.EcommerceStore.model.Order;
import com.EcommerceStore.model.User;

@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		Date date = new Date();
		
		ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
		User auth = (User) request.getSession().getAttribute("auth");
		
		if(cartList != null && auth != null) {
			for(Cart c : cartList) {
				Order order = new Order();
				order.setId(c.getId());
				order.setUid(auth.getId());
				order.setQuantity(c.getQuantity());
				order.setDate(formatter.format(date));
				
				OrderDao oDao = new OrderDao(DBconnection.getConnection());
				boolean result = oDao.insertOrder(order);
				
				if(!result) {
					PrintWriter pw = response.getWriter();
					pw.print("<h3 style='color:crimson; text-align:center'>Error</h3>");
					RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
					rd.include(request, response);
					break;
				}
			}
			cartList.clear();
			response.sendRedirect("orders.jsp");
			
			
		}
		else if(auth == null) {
			response.sendRedirect("login.jsp");
		}
		else {
			PrintWriter pw = response.getWriter();
			pw.print("<h3 style='color:crimson; text-align:center'>Cart Empty</h3>");
			RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
			rd.include(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
