<%@page import="com.EcommerceStore.dao.ProductDao"%>
<%@page import="java.util.List"%>
<%@page import="com.EcommerceStore.connection.DBconnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
HashSet<Integer> setOfProfuctID = new HashSet<>();
ProductDao pd = new ProductDao(DBconnection.getConnection());
List<Product> products = pd.getAllProduct();

ArrayList<Cart> cartlist = (ArrayList) session.getAttribute("cart-list");
if(cartlist != null){
	
	for(Cart id : cartlist){
		setOfProfuctID.add(id.getId());
	}
}

%>
<!DOCTYPE html>
<html>
<head>

<title>Shopping Web App</title>
<%@include file="includes/head.jsp"%>

</head>
<body>

	<%@include file="includes/nav.jsp"%>
	<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {%>
					<div class="col-md-3 my-3">
					<div class="card w-100" style="width: 18rem;">
						<img class="card-img-top" src="images/<%= p.getImage() %>"
							alt="Card image cap" >
						<div class="card-body">
							<h5 class="card-title"><%= p.getName() %></h5>
							<h6 class="price">Price : ₹ <%= p.getPrice() %></h6>
							<h6 class="category">Category : <%= p.getCategory() %></h6>
							<div class="mt-2 d-flex justify-content-between">
								<% 
									if(setOfProfuctID.contains(p.getId())){ %>	
									
										<a href="cart.jsp" class="btn btn-primary">Go to Cart</a>
										
								<%	} else{ %>
										<a href="AddToCart?id=<%=p.getId() %>" class="btn btn-dark">Add to Cart</a>
								<% 	}
								%>								
								
								<a href="order-now?id=<%= p.getId() %>&quantity=1" class="btn btn-warning">Buy Now</a>
							</div>

						</div>
					</div>
				</div>
				<%}
			}
			%>
			
		</div>
	</div>





















	<%@include file="includes/footer.jsp"%>

</body>
</html>