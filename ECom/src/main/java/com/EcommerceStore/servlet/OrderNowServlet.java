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
import java.util.*;


import com.EcommerceStore.connection.DBconnection;
import com.EcommerceStore.dao.OrderDao;
import com.EcommerceStore.model.Cart;
import com.EcommerceStore.model.Order;
import com.EcommerceStore.model.User;

@WebServlet("/OrderNowServlet")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		Date date = new Date();
		
		User auth = (User) request.getSession().getAttribute("auth");
		if(auth != null) {
			String productID = request.getParameter("id");
			int productQuantity = Integer.parseInt(request.getParameter("quantity"));
			
			if(productQuantity <= 0) {
				productQuantity = 1;
			}
			
			Order orderModel = new Order();
			orderModel.setId(Integer.parseInt(productID));
			orderModel.setUid(auth.getId());
			orderModel.setQuantity(productQuantity);
			orderModel.setDate(formatter.format(date));
			
			OrderDao orderDao = new OrderDao(DBconnection.getConnection());
			boolean result = orderDao.insertOrder(orderModel);
			
			if(result) {
				ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
				if(cartList != null) {
					for(Cart itemToRemove : cartList) {
						if(itemToRemove.getId() == Integer.parseInt(productID)) {
							cartList.remove(cartList.indexOf(itemToRemove));
							break;
						}
					}
				}
				response.sendRedirect("orders.jsp");
			}
			else {
				PrintWriter pw = response.getWriter();
				pw.print("<h3 style='color:crimson; text-align:center'>Order Failed</h3>");
				RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
				rd.include(request, response);
			}
			
		}
		else {
			response.sendRedirect("login.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
