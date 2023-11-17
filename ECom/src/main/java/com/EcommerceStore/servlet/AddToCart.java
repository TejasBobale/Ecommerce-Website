package com.EcommerceStore.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.EcommerceStore.model.Cart;

/**
 * Servlet implementation class AddToCart
 */
@WebServlet("/AddToCart")
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		
		ArrayList<Cart> cartList = new ArrayList<>();
		
		int id = Integer.parseInt(request.getParameter("id"));
		Cart cartItem = new Cart();
		cartItem.setId(id);
		cartItem.setQuantity(1);
		String exist = "false";
		
		
		HttpSession session = request.getSession();
		

		ArrayList<Cart> existingCartList = (ArrayList<Cart>) session.getAttribute("cart-list");
		if(existingCartList == null) {
			cartList.add(cartItem);
			session.setAttribute("cart-list", cartList);
			response.sendRedirect("index.jsp");
		}
		else {
			cartList = existingCartList;
			boolean itemExist = false;
			
			for(Cart c : cartList) {
				if(c.getId() == id) {
					itemExist = true;
					PrintWriter pw = response.getWriter();
					pw.print("<h3 style='color:crimson; text-align:center'>Item already in the cart.  <a href='cart.jsp'>Go to cart</a></h3>");
					RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
					rd.include(request, response);
					break;
				}
			}

			if(!itemExist) {
				cartList.add(cartItem);
				response.sendRedirect("index.jsp");
			}
			
		}
		 
	}

}
