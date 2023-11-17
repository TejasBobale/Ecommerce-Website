<%@page import="com.EcommerceStore.connection.DBconnection"%>
<%@page import="com.EcommerceStore.dao.ProductDao"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 ArrayList<Cart> cartList = (ArrayList) session.getAttribute("cart-list");
 List<Cart> cartProduct = null;
 
 if(cartList != null){
	 ProductDao pDao = new ProductDao(DBconnection.getConnection());
	 cartProduct = pDao.getCartProducts(cartList);
	 request.setAttribute("cart_list", cartList);
 }
 
 
 
 %>   
    
<!DOCTYPE html>
<html>
<head>

<title>Cart</title>
<%@include file = "includes/head.jsp" %>
<style type="text/css">
	.table tbody td{
		vertical-align: middle;
	}
	.btn-incre, .btn-decre{
		box-shadow: none;
		font-size: 25px
	}
</style>
</head>
<body>

	<%@include file="includes/nav.jsp" %>
	
	<div class="container">
		<div class="d-flex py-3">
			<h3>Total price: ₹ 100</h3><a href="orders.jsp" class="mx-3 btn btn-primary">Check out</a>
		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Buy Now</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				
				<%
				if(cartProduct != null){
					for(Cart c : cartProduct){ 						
					%>
						<tr>
						<td><%= c.getName() %></td>
						<td><%= c.getCategory() %></td>
						<td>₹ <%= c.getPrice() %></td>
						<td>
							<form action="" method="post" class="form-inline">
								<input type="hidden" name="id" value="<%= c.getId() %>" class="form-input">
								<div class="form-group d-flex justify-content-between">
									<a class="btn btn-sm btn-decre" href="#"> <i class="fas fa-minus-square"></i> </a>
									<input type="number" name="quantity" class="form-control" value="1" readonly> 
									<a class="btn btn-sm btn-incre" href="#"> <i class="fas fa-plus-square"></i> </a>
								</div>
							</form>
						</td>
						<td> <a href="#" class="btn btn-sm btn-danger">Remove</a> </td>
					</tr>
				<%	}
					
				}
				
				%>
				
			</tbody>
		</table>		
	</div>	
	
	
	
	
	
	<%@include file = "includes/footer.jsp" %>
	
</body>
</html>