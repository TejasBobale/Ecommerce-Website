package com.EcommerceStore.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import com.EcommerceStore.model.Cart;

@WebServlet("/RemoveFromCart")
public class RemoveFromCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		if(id != null) {
			ArrayList<Cart> cartList = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
			if(cartList != null) {
				for(Cart itemToRemove : cartList) {
					if(itemToRemove.getId() == Integer.parseInt(id)) {
						cartList.remove(cartList.indexOf(itemToRemove));
						break;
					}
				}
				RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");
				rd.include(request, response);
			}
		}
		
	}

	

}
