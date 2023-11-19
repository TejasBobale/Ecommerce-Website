<%@page import="java.text.DecimalFormat"%>
<%@page import="com.EcommerceStore.connection.DBconnection"%>
<%@page import="com.EcommerceStore.dao.ProductDao"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);

ArrayList<Cart> cartlist = (ArrayList) session.getAttribute("cart-list");
if(cartlist != null){
	ProductDao pdao = new ProductDao(DBconnection.getConnection());
	double total = pdao.getTotalPrice(cartlist);
	request.setAttribute("totalPrice", total);
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
			<h3>Total price: ₹ ${ (totalPrice>0) ? dcf.format(totalPrice) : 0 }</h3><a href="check-out?price=${ totalPrice }" class="mx-3 btn btn-primary">Check out</a>
		</div>
		<table class="table" style="text-align:center">
			<thead class="table-secondary">
				<tr>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Quantity</th>
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
						<td>₹ <%= dcf.format(c.getPrice()) %></td>
						<td>
							<form action="" method="post" class="form-inline" >
								<input type="hidden" name="id" value="<%= c.getId() %>" class="form-input">
								<div class="form-group d-flex ">
									<a class="btn btn-sm btn-decre" href="QantitySetter?action=dec&id=<%= c.getId()%>"> <i class="fas fa-minus-square"></i> </a>
									<input type="number" name="quantity" class="form-control" value="<%= c.getQuantity() %>" readonly> 
									<a class="btn btn-sm btn-incre" href="QantitySetter?action=inc&id=<%= c.getId()%>"> <i class="fas fa-plus-square"></i> </a>
									 <!-- <a href="order-now?id=< c.getId() >&quantity=< c.getQuantity() >" class="mx-3 btn btn-primary">Check out</a> -->
								</div>
							</form>
						</td>
						<td> <a href="remove-from-cart?id=<%= c.getId() %>" class="btn btn-sm btn-danger">Remove</a> </td>
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