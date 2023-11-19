<%@page import="java.text.DecimalFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
User authO = (User) request.getSession().getAttribute("auth");
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
List<Order> orders = null;
if(authO != null){
	orders = new OrderDao(DBconnection.getConnection()).userOrder(authO.getId());
}
else{
	response.sendRedirect("login.jsp");
}
%>
    
<!DOCTYPE html>
<html>
<head>

<title>My Orders</title>
<%@include file = "includes/head.jsp" %>

</head>
<body>
	<%@include file="includes/nav.jsp" %>
	
	<div class="container">
		
		<table class="table" style="text-align:center">
			<thead class="table-secondary">
				<tr>
				<!--  	<th scope="col">Image</th> -->
					<th scope="col">Date</th>
					<th scope="col">Name</th>
					<th scope="col">Category</th>
					<th scope="col">Price</th>
					<th scope="col">Quantity</th>
					<th scope="col">Cancel</th>
				</tr>
			</thead>
			<tbody>
				
				<%
					if(orders != null){
											
					 	for(Order o : orders){ %>
							
							<tr>
								<td><%= o.getDate() %></td>
								<td><%= o.getName() %></td>
								<td><%= o.getCategory() %></td>
								<td><%= dcf.format(o.getPrice()) %></td>
								<td><%= o.getQuantity() %></td>
								<td> <a href="cancel-order?id=<%= o.getOrderID() %>" class="btn btn-sm btn-danger">Cancel</a> </td>
							</tr>
							
							
					<%	}
					 	
					}
				%>
				
			</tbody>
		</table>		
	</div>
	
	<%@include file="includes/footer.jsp" %>
	
	
</body>
</html>