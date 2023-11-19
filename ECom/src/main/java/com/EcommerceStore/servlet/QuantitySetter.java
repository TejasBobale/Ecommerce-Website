package com.EcommerceStore.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.EcommerceStore.model.Cart;

@WebServlet("/QantitySetter")
public class QuantitySetter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		String action = request.getParameter("action");
		int id = Integer.parseInt(request.getParameter("id"));
		
		
		ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
		
		if(action != null && id > 0) {
			if(action.equals("inc")) {
				for(Cart c : cart_list) {
					if(c.getId() == id) {
						int quantity = c.getQuantity();
						quantity++;
						c.setQuantity(quantity);
						break;
					}
				}
				RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
				rd.include(request, response);
			}
			else if(action.equals("dec")) {
				for(Cart c : cart_list) {
					if(c.getId() == id && c.getQuantity() > 1) {
						int quantity = c.getQuantity();
						quantity--;
						c.setQuantity(quantity);
						break;
					}
				}
				RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
				rd.include(request, response);
			}
		}
		else {
			PrintWriter pw = response.getWriter();
			pw.print("<h3 style='color:crimson; text-align:center'>Error</h3>");
			RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
			rd.include(request, response);
		}
	}

}
